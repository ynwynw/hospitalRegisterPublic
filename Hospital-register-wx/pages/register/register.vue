<template>
	<view class="content b-t">
		<view class="carousel-section">
			<!-- 标题栏和状态栏占位符 -->
			<view class="titleNview-placing"></view>
			<!-- 背景色区域 -->
			<view class="titleNview-background" :style="{backgroundColor:titleNViewBackground}"></view>

			<swiper class="carousel" circular @change="swiperChange">
				<swiper-item class="carousel-item">
					<image src="/static/预约挂号2.jpg" mode="scaleToFill" />
				</swiper-item>
			</swiper>
			<!-- 自定义swiper指示器 -->
			<view class="swiper-dots">
				<input type="text" disabled="" placeholder="点击搜索医院/医生" @click="chageSearchPage"></input>

			</view>
		</view>

		<view class="container">
			<view class="tabs">
				<view class="tab" :class="{active: activeTab === 'department'}" @click="toggleTab('department')">选医院
				</view>
				<view class="tab" :class="{active: activeTab === 'doctor'}" @click="toggleTab('doctor')">选医生</view>
			</view>
			<view class="order-item" v-for="(item, index) in registerList" :key="index">
				<view class="goods-box-single" @tap="goChooseDatePage(item)">
					<view class="goods-img">
						<image :src="urlKey+item.logo" mode="aspectFill" style="width: 100%;height: 100%;"></image>
					</view>

					<view class="right">
						<text class="title clamp">{{item.name}}</text>
						<text class="attr-box">{{item.hospitalName}}</text><br>
						<!-- <text class="attr-box">{{item.introduce.length>limit ?  item.introduce.slice(0, limit) + '...' : item.introduce}}</text> -->
						<view class="down">
							
							<text class="price">{{item.registerName}}</text>
							<view class="action1">
								<view class="action-btn1" @tap="goChooseDatePage(item)">预约</view>

							</view>
						</view>

					</view>
				</view>

			</view>

			<view class="order-item" v-for="(item, index) in hospitaLList" :key="index">
				<view class="goods-box-single" @tap="goChooseDatePage(item)">
					<view class="goods-img">
						<image :src="urlKey+item.logo" mode="aspectFill" style="width: 100%;height: 100%;"></image>
					</view>

					<view class="right">
						<text class="title clamp">{{item.name}}</text>
						<text class="title1 clamp">去预约</text>

					</view>
				</view>

			</view>


		</view>
	</view>
</template>

<script>
	import {
		mapState
	} from 'vuex';
	export default {

		data() {
			return {
				limit: 22,
				source: 0,
				registerList: [],
				serviceList: [],
				hospitalName:'',
				hospitaLList: [],
				urlKey: getApp().globalData.url + "/",
				activeTab: 'department', // 当前选中的标签页


			}
		},
		computed: {
			...mapState(['hasLogin', 'userInfo', 'citys'])
		},
		onLoad: function(option) {
			console.log("option", option)
			console.log("3333", this.citys)
			this.getRegisterList();

		},

		methods: {
			getHospital(){
				let hospitalId = this.registerList.hospitalId
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
				console.log("item2222", item)
				if (this.activeTab == 'doctor') {
					uni.navigateTo({
						url: `/pages/detail/detail?service=${JSON.stringify(item)}`
					})
				} else if (this.activeTab == 'department') {
					uni.navigateTo({
						url: `/pages/detail2/detail2?register=${JSON.stringify(item)}`
					})
				}

			},
			toggleTab(tab) { // 切换标签页
				this.activeTab = tab;
				if (tab == 'department') {
					this.getRegisterList();
				} else if (tab == 'doctor') {
					this.getServiceList();
				}
			},
			viewDepartment(dept) { // 查看科室详情
				uni.showToast({
					title: `您正在查看科室：${dept.name}`,
					icon: 'none',
				});
				// TODO：跳转到科室详情页面
			},
			viewDoctor(doc) { // 查看医生详情
				uni.showToast({
					title: `您正在查看医生：${doc.name}`,
					icon: 'none',
				});
				// TODO：跳转到医生详情页面
			},



			chageSearchPage() {
				uni.navigateTo({
					url: `/pages/search/search`
				})
			},
			goServicePage(item) {
				uni.navigateTo({
					url: `/pages/register/service?data=${JSON.stringify(item)}`
				})
			},
			getServiceList() {
				let that = this;
				let areaId = this.citys.areaId
				// let registerId = that.registerData.id;
				uni.request({
					url: getApp().globalData.url + '/hospital_mini/getDoctorListByAreaId',
					method: 'POST',
					data: {
						// "registerId": registerId
						"areaId": areaId
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						if (res.data.code == '0') {
							this.hospitaLList = []
							that.registerList = res.data.data;
							console.log("res.data.data", res.data.data)
						}
					}
				});
			},
			//获取地址列表
			getRegisterList() {
				let that = this;
				let areaId = this.citys.areaId
				uni.request({
					url: getApp().globalData.url + '/hospital_mini/getHospitalList',
					method: 'POST',
					data: {
						"areaId": areaId
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						if (res.data.code == '0') {
							this.registerList = []
							that.hospitaLList = res.data.data;
							console.log("areaId", that.hospitaLList)
						}
					}
				});
			}
		}
	}
</script>

<style lang='scss'>
	page {
		padding-bottom: 120upx;
		background: #f8f8f8;
	}

	.container {
		display: flex;
		flex-direction: column;
		height: 100%;



		.order-item {
			display: flex;
			flex-direction: column;
			padding-left: 30upx;
			background: #fff;

			margin-top: 10rpx;


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
					padding: 30rpx;
					overflow: hidden;

					.title {
						font-size: $font-base;
						color: #000;
						/* line-height: 1; */
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
							font-size: 25rpx;
							color: #404144;

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
								color: skyblue;
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

		}

	}

	.tabs {
		display: flex;
		justify-content: space-around;
		align-items: center;
		height: 70rpx;
		border-bottom: 1px solid #eee;
		background-color: #fff;
		margin-top: 10rpx;
		margin-bottom: 10rpx;
	}

	.tab {
		width: 150rpx;
		height: 70rpx;
		font-size: 35rpx;
		color: #999;
		/* cursor: pointer; */
		transition: all 0.3s;
		user-select: none;
		line-height: 70rpx;
		text-align: center;
		/* border: 1px solid #000; */
	}

	.tab.active {
		width: 150rpx;
		height: 70rpx;
		color: skyblue;
		font-weight: bold;
		line-height: 70rpx;
		border-bottom: 5rpx solid skyblue;
	}

	.list {
		flex: 1;
		overflow: auto;
		padding: 20rpx;
	}

	.item {
		margin-bottom: 20rpx;
		padding: 20rpx;
		border: 1px solid #eee;
		border-radius: 10rpx;
		/* cursor: pointer; */
		transition: all 0.3s;
		user-select: none;
	}

	.item:hover {
		background-color: #f5f5f5;
	}

	.carousel-section {
		/* position: fixed; */
		position: relative;
		padding-top: 10px;

		.titleNview-placing {
			height: var(--status-bar-height);
			padding-top: 44px;
			box-sizing: content-box;
		}

		.titleNview-background {
			position: absolute;
			top: 0;
			left: 0;
			width: 100%;
			height: 350upx;
			transition: .4s;
		}
	}

	.carousel {
		width: 100%;
		height: 350upx;

		.carousel-item {
			width: 100%;
			height: 100%;
			padding: 0 28upx;
			overflow: hidden;
		}

		image {
			width: 100%;
			height: 100%;
			/* border-radius: 10upx; */
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



	.carousel-section {
		padding: 0;

		.titleNview-placing {
			padding-top: 0;
			height: 0;
		}

		.carousel {
			.carousel-item {
				padding: 0;
			}
		}

		.swiper-dots {
			width: 90%;
			margin-left: 5%;
			height: 60rpx;
			border: 1px solid #a6d1f6;
			margin-top: 10rpx;
			text-align: center;
			background-color: #fff;
			border-radius: 125rpx 125rpx 125rpx 125rpx;
			box-shadow: 1rpx 1rpx 1rpx 1rpx #e2e2e2;
			font-size: 30rpx;
			/* padding: 5rpx; */
			/* line-height: 60rpx; */
			
		}


	}
</style>




<!-- 
<template>

	<view class="components-tabs tn-safe-area-inset-bottom">

		<view :style="{paddingTop: vuex_custom_bar_height + 'px'}">

			<demo-title title="自定义标签宽高" :contentPadding="false">
				<tn-tabs :list="scrollList" :current="current" :height="120" :itemWidth="200" :barWidth="140"
					@change="tabChange"></tn-tabs>
				<view class="illness">
					<view class="bannerBox">
						<view class="bannerBox-item2" v-for="(item,index) in illnessList" :key="index"">
									<image :src=" urlKey+item.logo " mode="aspectFill">
							</image>
							<view class="text_right">
								<view class="right1">
									<view class="txt">{{item.name}}</view>
								</view>
								<view class="right2">
									<button margin="10rpx 10rpx">预约</button>

								</view>
							</view>

						</view>
					</view>
				</view>
			</demo-title>
			<view class="tn-padding-bottom-lg"></view>

		</view>

	</view>

</template>

<script>
	import demoTitle from '@/libs/components/demo-title.vue'
	export default {
		name: 'componentsTabs',
		components: {
			demoTitle
		},
		data() {
			return {
				source: 0,
				registerList: [],
				serviceList: [],
				urlKey: getApp().globalData.url + "/",
				current: 0,
				activeItemStyle: {
					borderTop: '1rpx solid #E6E6E6'
				},
				barStyle: {
					boxShadow: `12rpx 12rpx 16rpx #01BEFF`
				},
				scrollList: [
					{
						name: '查看科室'
					},
					{
						name: '查看医生'
					}

				],
			
				illnessList: [],

			}
		},
		onLoad(option){
			this.getRegisterList();
			this.getServiceList();
		},
		methods: {
			getRegisterList(){
				let that = this;
				uni.request({
					url: getApp().globalData.url + '/hospital_mini/getRegisterList',
					method: 'POST',
					data: {
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						if(res.data.code == '0'){
							console.log("this",res.data.data)
							that.registerList = res.data.data;
						}
					}
				});
			},
			
			getServiceList(){
				let that = this;
				uni.request({
					url: getApp().globalData.url + '/hospital_mini/getRegisterList',
					method: 'POST',
					data: {
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						if(res.data.code == '0'){
							console.log("ysheng",res.data.data)
							that.serviceList = res.data.data;
						}
					}
				});
			},
			
			// tab选项卡切换
			tabChange(index) {
				console.log("index", index)
				this.current = index
				if (index == 0) {
					this.illnessList = this.registerList;
				} else {
					console.log("4444")
					this.illnessList=[]
					this.illnessList = this.serviceList;
				}

			}
		}

	}
</script>

<style lang="scss" scoped>
	.components-tabs {
		background-color: $tn-bg-gray-color;
		min-height: 100vh;



		.illness {
			margin: 10rpx 20rpx;
			height: 1180rpx;

			.illnessTop {
				width: 100%;
				height: 70rpx;
				display: flex;
				flex-direction: row;
				justify-content: space-between;

				text {
					// font-size: $titleSize;
					// color: $titleColor;
					font-weight: bold;
					line-height: 70rpx;
				}

				.list-item {
					width: 170rpx;
					height: inherit;
					display: flex;
					flex-direction: row;
					justify-content: flex-end;

					text {
						// font-size: $minorSize;
						// color: $minorColor;
						font-weight: 600;
						line-height: 70rpx;
						padding-right: 5rpx;
					}

					image {
						width: 20rpx;
						height: 20rpx;
						margin: 25rpx 0;
					}
				}
			}

			.bannerBox {
				width: 100%;
				height: 1100rpx;
				margin-top: 10rpx;
				border-top-left-radius: 15rpx;
				border-top-right-radius: 15rpx;
				overflow: hidden;
				background-color: #FFF;

				.bannerBox-item {
					width: inherit;
					height: 380rpx;
					position: relative;
					top: 0;

					image {
						width: inherit;
						height: inherit;
						position: absolute;
						top: 0;
					}



					text {

						width: inherit;
						height: inherit;
						margin: 0 20rpx 5rpx;
						// font-size: $titleSize;
						font-weight: 900;
						position: absolute;
						bottom: 0;
						color: #FFF;


					}



				}

				.bannerBox-item2 {
					height: 210rpx;
					padding: 10rpx;
					margin-bottom: 5rpx;
					border-bottom: 1rpx solid #EBEEF5;
					display: flex;
					flex-direction: row;

					image {
						width: 120rpx;
						height: 120rpx;
						margin-top: 40rpx;
					}

					.text_right {
						height: inherit;
						width: calc(100% - 120rpx);
						display: flex;

						.right1 {
							width: calc(100% - 200rpx);
							height: inherit;

							.txt {

								width: 360rpx;
								height: 210rpx;
								text-align: center;
								line-height: 210rpx;
								background: yellowgreen;
								font-size: 25rpx;

							}
						}

						.right2 {
							width: 130rpx;
							height: 210rpx;
							line-height: 210rpx;
							text-align: center;
							margin-left: 50rpx;

							button {
								
								font-size: 27rpx;
								margin-top: 70rpx;
								border: 1rpx solid skyblue;
								background-color: #FFF;
								color: skyblue;
							}
						}
					}
				}
			}
		}
	}
</style>
 -->
