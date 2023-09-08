<template>
	<view class="">

		<view class='header'>
			<view class='search'>
				<input type="text" focus placeholder="输入医院/医生" @input="onKeyInput" v-model="name_value"></input>
			</view>
		</view>
		<view class="cont">
			<!-- <view class="order-item" v-for="(item, index) in registerList" :key="index" v-if="registerList">
				<view class="goods-box-single">
					<image class="goods-img" :src="urlKey+item.logo" mode="aspectFit"></image>
					<view class="right">
						<text class="title clamp">{{item.name}}</text>
						<text class="attr-box">{{item.introduce}}</text>
						<view class="down">
							<text class="price">{{item.typeInfo}}</text>
							<view class="action1">
								<view class="action-btn1" @tap="goChooseDatePage(item)">预约</view>

							</view>
						</view>
					</view>
				</view>
				<view class="action-box b-t">
					<button class="action-btn recom" @click="goServicePage(item)">查看医生</button>
				</view>
			</view> -->
			<view class="order-item" v-for="(item, index) in registerList" :key="index">
				<view class="goods-box-single" @tap="goChooseDatePage(item)">
					<view class="goods-img">
						<image :src="urlKey+item.logo" mode="aspectFit" style="width: 100%;height: 100%;"></image>
					</view>

					<view class="right">
						<text class="title clamp">{{item.name}}</text>
						<!-- <text
							class="attr-box">{{item.introduce.length>limit ?  item.introduce.slice(0, limit) + '...' : item.introduce}}</text>
						<view class="down">
							<text class="price">{{item.typeInfo}}</text>
							<view class="action1">
								<view class="action-btn1" @tap="goChooseDatePage(item)">预约</view>

							</view>
						</view> -->
							<text class="title1 clamp">去预约</text>
					</view>
				</view>
				<!-- <view class="action-box b-t">
					<view class="action-btn recom" @click="goChooseDatePage(item)">预约</view>
				</view> -->
			</view>
		<!-- 	<view class="order-item" v-for="(item, index) in dockerList" :key="index" v-if="dockerList">
				<view class="goods-box-single">
					<image class="goods-img" :src="urlKey+item.logo" mode="aspectFit"></image>
					<view class="right">
						<text class="title clamp">{{item.name}}</text>
						<text class="attr-box">{{item.introduce}}</text>
						<view class="down">
							<text class="price">{{item.typeInfo}}</text>
							<view class="action1">
								<view class="action-btn1" @tap="goChooseDatePage(item)">预约</view>

							</view>
						</view>
					</view>
				</view>
				<view class="action-box b-t">
					<button class="action-btn recom" @click="goChooseDatePage(item)">预约挂号</button>
				</view>
			</view> -->
			<view class="order-item" v-for="(item, index) in dockerList" :key="index" v-if="dockerList">
				<view class="goods-box-single" @tap="goChooseDatePage(item)">
					<view class="goods-img">
						<image :src="urlKey+item.logo" mode="aspectFill" style="width: 100%;height: 100%;"></image>
					</view>
					
					<view class="right">
						<text class="title clamp">{{item.name}}</text>
						<text class="attr-box">{{item.hospitalName}}</text>
						<!-- <text class="attr-box">{{item.introduce.length>limit ?  item.introduce.slice(0, limit) + '...' : item.introduce}}</text> -->
						<view class="down">
							<text class="price">{{item.registerName}}</text>
							<view class="action1">
								<view class="action-btn1" @tap="goChooseDatePage(item)">预约</view>
			
							</view>
						</view>
			
					</view>
				</view>
				<!-- <view class="action-box b-t">
					<view class="action-btn recom" @click="goChooseDatePage(item)">预约</view>
				</view> -->
			</view>
		</view>
		<view class="mess_text" v-if="message">{{message}}</view>
	</view>
</template>

<script>
	import {
		mapState
	} from 'vuex';
	export default {
		data() {

			return {
				name_value: "",
				searchWaiting: false,
				message: "请先输入医院/医生...",
				countTime: 1000, //延迟搜索 时间
				inputValue: "",
				hospitalId:'',
				hospitalName:'',
				searchList: [],
				registerList: [],
				dockerList: [],
				urlKey: getApp().globalData.url + "/",
				limit: 22,
				msg: ''
			}
		},
		computed: {
			...mapState(['hasLogin', 'userInfo', 'citys'])
		},
		methods: {
			getHospital(){
				let hospitalId = this.hospitalId
				uni.request({
					url: getApp().globalData.url + '/hospital_mini/getHospitalByHospitalId',
					method: 'POST',
					data: {
						
						"hospitalId":hospitalId
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						if (res.data.code == '0') {
							// that.collectList = res.data.data;
							this.hospitalName = res.data.data.hospitalName
							console.log("res.data.data6767", res.data.data.hospitalName)
						}
					}
				});
			},
			goChooseDatePage(item) {
				console.log("item2222",item)
				if(this.msg == '医生'){
					uni.navigateTo({
						url: `/pages/detail/detail?service=${JSON.stringify(item)}`
					})
				}
				else if(this.msg == '科室'){
					uni.navigateTo({
						url: `/pages/detail2/detail2?register=${JSON.stringify(item)}`
					})
				}
				
			},
			goServicePage(item) {
				uni.navigateTo({
					url: `/pages/register/service?data=${JSON.stringify(item)}`
				})
			},
			timer: function() {
				var that = this;
				this.searchWaiting = true
				let promise = new Promise((resolve, reject) => {
					let setTimer = setInterval(
						() => {
							console.log('搜索倒计时: ' + that.countTime);
							this.countTime = this.countTime - 1000
							if (this.countTime <= 0) {
								console.log('开始搜索: ' + that.inputValue);

								this.countTime = 1000,
									this.searchWaiting = false,

									resolve(setTimer)
							}
						}, 1000)
				})
				promise.then((setTimer) => {
					that.getSearch();
					clearInterval(setTimer) //清除计时器
				})
			},

			onKeyInput: function(event) {
				this.countTime = 1000
				this.inputValue = event.target.value
				if (!this.searchWaiting) {
					this.timer();
				}

			},
			getSearch() {
				let that = this;
				that.registerList = [];
				that.dockerList = [];
				that.searchList = [];
				that.message = "";
				let areaId = this.citys.areaId
				let inputValue = that.inputValue;
				uni.request({
					url: getApp().globalData.url + '/hospital_mini/search',
					method: 'POST',
					data: {
						"name": inputValue,
						"areaId": areaId
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						console.log("111", res)
						if (res.data.msg == '医生') {
							that.dockerList = res.data.data;
							this.msg = res.data.msg
							this.hospitalId = res.data.data.hospitalId
							this.getHospital()
							console.log("33333", that.dockerList)
						} else if (res.data.msg == '科室') {
							that.registerList = res.data.data;
							this.msg = res.data.msg
							console.log("4444", that.registerList)
						} else if (res.data.msg == '找不到') {
							that.message = '无搜索结果...';
						} else if (res.data.msg == '名字为空') {
							that.message = '请先输入医院/医生...';
						}
					}
				});


			}
		},
		
	}
</script>

<style lang="scss">
	page {
		background: #f1f1f1;
	}



	.mess_text {
		width: 100%;
		height: 60rpx;
		line-height: 60rpx;
		font-size: 28rpx;
		color: #acacac;
		// background-color: red;
		padding-left: 20rpx;
	}

	.header {
		background: #fff;
		// margin-bottom: 30rpx;
		padding-top: 20rpx;
		padding-bottom: 20rpx;

	}

	.cont {
		// background: #fff;
		// margin-bottom: 30rpx;
		// padding-top: 20rpx;
		// padding-bottom: 20rpx;


		.order-item {
			display: flex;
			flex-direction: column;
			padding-left: 30upx;
			background: #fff;
			margin-top: 16upx;

			.i-top {
				display: flex;
				align-items: center;
				height: 80upx;
				padding-right: 30upx;
				font-size: $font-base;
				color: $font-color-dark;
				position: relative;

				.time {
					flex: 1;
				}

				/* .state{
				color: $base-color;
			} */
				.del-btn {
					padding: 10upx 0 10upx 36upx;
					font-size: $font-lg;
					color: $font-color-light;
					position: relative;

					&:after {
						content: '';
						width: 0;
						height: 30upx;
						border-left: 1px solid $border-color-dark;
						position: absolute;
						left: 20upx;
						top: 50%;
						transform: translateY(-50%);
					}
				}
			}

			/* 多条商品 */
			.goods-box {
				height: 160upx;
				padding: 20upx 0;
				white-space: nowrap;

				.goods-item {
					width: 120upx;
					height: 120upx;
					display: inline-block;
					margin-right: 24upx;
				}

				.goods-img {
					display: block;
					width: 100%;
					height: 100%;
				}
			}

			/* 单条商品 */
			.goods-box-single {
				display: flex;

				.goods-img {
					display: block;
					width: 100upx;
					height: 100upx;
					margin-top: 40rpx;
					border: 1px solid #ccc;
					border-radius: 10%;
				}

				.right {
					flex: 1;
					display: flex;
					flex-direction: column;
					padding: 30upx 30upx 0 24upx;
					overflow: hidden;

					.title {
						font-size: $font-base;
						color: #000;
						line-height: 1;
						font-weight: bolder;
						padding: 10rpx 12rpx;
					}
					.title1 {
						font-size: 24rpx;
						color: skyblue;
						/* line-height: 1; */
						font-weight: bolder;
						padding: 10rpx 12rpx;
					}

					.attr-box {
						font-size: $font-sm + 2upx;
						color: $font-color-light;
						padding: 10upx 12upx;
					}

					.down {
						margin-bottom: 15rpx;
						width: 100%;
						/* background-color: #b36d41; */
						display: flex;

						.price {
							/* background-color: #909399; */
							width: 80%;
							font-size: $font-base + 2upx;
							color: $font-color-dark;

							&:before {
								content: '';
								font-size: $font-sm;
								margin: 0 2upx 0 8upx;
							}

						}

						.action1 {
							/* background-color: #ffb4c7; */
							width: 20%;
							font-size: 26rpx;

							.action-btn1 {
								height: 60rpx;
								font-size: 25rpx;
								color: #909399;
								border: 1px solid skyblue;
								border-radius: 10rpx;
								line-height: 60rpx;
								text-align: center;
							}
						}

					}

				}
			}

			.price-box {
				display: flex;
				justify-content: flex-end;
				align-items: baseline;
				padding: 20upx 30upx;
				font-size: $font-sm + 2upx;
				color: $font-color-light;

				.num {
					margin: 0 8upx;
					color: $font-color-dark;
				}

				.price {
					font-size: $font-lg;
					color: $font-color-dark;

					&:before {
						content: '￥';
						font-size: $font-sm;
						margin: 0 2upx 0 8upx;
					}
				}
			}

			.action-box {
				display: flex;
				justify-content: flex-end;
				align-items: center;
				height: 60upx;
				position: relative;
				padding-right: 30upx;
			}

			/* 	.action-btn {
			width: 110upx;
			height: 50upx;
			margin: 0;
			margin-left: 24upx;
			padding: 0;
			text-align: center;
			line-height: 50upx;
			font-size: $font-sm;
			color: $font-color-dark;
			background: #fff;
			border-radius: 100px;

			&:after {
				border-radius: 100px;
			}

			&.recom {
				background: #fff9f9;
				color: $base-color;

				&:after {
					border-color: #f7bcc8;
				}
			}
		} */
		}
	}

	.content {
		position: relative;
	}

	.list {
		display: flex;
		align-items: center;
		padding: 20upx 30upx;
		;
		background: #fff;
		position: relative;
	}

	.wrapper {
		display: flex;
		flex-direction: column;
		flex: 1;
	}

	.address-box {
		display: flex;
		align-items: center;

		.tag {
			font-size: 24upx;
			color: $base-color;
			margin-right: 10upx;
			background: #fffafb;
			border: 1px solid #ffb4c7;
			border-radius: 4upx;
			padding: 4upx 10upx;
			line-height: 1;
		}

		.address {
			font-size: 30upx;
			color: $font-color-dark;
		}
	}

	.u-box {
		font-size: 28upx;
		color: $font-color-light;
		margin-top: 16upx;

		.name {
			margin-right: 30upx;
		}
	}

	.icon-bianji {
		display: flex;
		align-items: center;
		height: 80upx;
		font-size: 40upx;
		color: $font-color-light;
		padding-left: 30upx;
	}

	.add-btn {
		position: fixed;
		left: 30upx;
		right: 30upx;
		bottom: 16upx;
		z-index: 95;
		display: flex;
		align-items: center;
		justify-content: center;
		width: 690upx;
		height: 80upx;
		font-size: 32upx;
		color: #fff;
		background-color: $base-color;
		border-radius: 10upx;
		box-shadow: 1px 2px 5px rgba(219, 63, 96, 0.4);
	}



	.search {
		height: 60rpx;
		line-height: 60rpx;
		background: #e7e7e7;
		border-radius: 20rpx;
		margin: 20rpx;
	}

	.search input {
		font-size: 28rpx;
		color: #acacac;
		width: 100%;
		height: 100%;
		padding-left: 20rpx;
	}
</style>
