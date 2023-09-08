<template>
	<view class="content">
		<view class="navbar">
			<view v-for="(item, index) in navList" :key="index" class="nav-item"
				:class="{current: tabCurrentIndex === index}" @click="tabClick(index)">
				{{item.text}}
			</view>
		</view>

		<swiper :current="tabCurrentIndex" class="swiper-box" duration="300" @change="changeTab">
			<swiper-item class="tab-content" v-for="(tabItem,tabIndex) in navList" :key="tabIndex">
				<scroll-view class="list-scroll-content" scroll-y>
					<!-- 空白页 -->
					<empty v-if="tabItem.loaded === true && tabItem.orderList.length === 0"></empty>

					<!-- 订单列表 -->
					<view v-if="tabItem.orderList != null && tabItem.orderList != ''" class="">
						<view v-for="(item,index) in tabItem.orderList" :key="index" class="order-item">
							<view class="i-top b-b" @click="goToDetail(item)">
								<view class="time">{{item.stateTip}}</view>
								<view class="state"><img src="/static/go.png" alt="" style="width: 100%; height: 100%;">
								</view>
							</view>
							<view class="goods-box-single">
								<image class="goods-img" :src="urlKey+item.serviceinfo.logo" mode="scaleToFill"></image>
								<view class="right">
									<text class="title clamp">{{item.serverName}}</text>
									<text class="attr-box">科室：{{item.registerName}}</text>
									<text class="attr-box">挂号费：￥{{item.price}}</text>
									<text class="price">就诊时间：{{item.orderDate}} {{item.orderTime}}</text>
								</view>
							</view>

						</view>
					</view>
					<view class="txt" v-else>
						<text>无挂号记录！</text>
					</view>
				</scroll-view>
			</swiper-item>
		</swiper>
	</view>
</template>

<script>
	import {
		mapState
	} from 'vuex';
	import uniLoadMore from '@/components/uni-load-more/uni-load-more.vue';
	import empty from "@/components/empty";
	import Json from '@/Json';
	export default {
		components: {
			uniLoadMore,
			empty
		},
		data() {
			return {
				// tabCurrentIndex: '',
				tabCurrentIndex: '',
				urlKey: getApp().globalData.url + "/",
				navList: [{
						// state: 0,
						text: '全部',
						// loadingType: 'more',
						orderList: []
					},
					// {
					// 	// state: 1,
					// 	// text: '待就诊',
					// 	loadingType: 'more',
					// 	orderList: []
					// }
				],
				discountStr: '无折扣',
				discount: '1',
				isBuycard: '0'
			};
		},

		computed: {
			...mapState(['userInfo'])
		},

		onLoad(options) {
			this.loadData()
			/**
			 * 修复app端点击除全部订单外的按钮进入时不加载数据的问题
			 * 替换onLoad下代码即可
			 */
			// this.tabCurrentIndex = options.state;
			console.log('this.tabCurrentIndex111', this.tabCurrentIndex)
			// #ifndef MP
			// this.loadData(this.tabCurrentIndex)
			// #endif
			// #ifdef MP
			// if (options.state == 0) {
			// 	this.tabCurrentIndex = options.state
			// 	console.log('this.tabCurrentIndex', this.tabCurrentIndex)
			// 	this.loadData(this.tabCurrentIndex)
			// } else if (options.state == 1) {
			// 	console.log('this.tabCurrentIndex', this.tabCurrentIndex)
			// 	this.tabCurrentIndex = options.state
			// 	this.loadData(this.tabCurrentIndex)
			// }
			// #endif

		},

		methods: {

			//获取订单列表
			loadData(source) {
				//这里是将订单挂载到tab列表下
				let index = this.tabCurrentIndex;
				// let index = source;
				// let index = this.tabCurrentIndex;
				console.log("当前tab===" + index)
				let navItem = this.navList[index];
				// let state = navItem.state;
				// console.log("state", state)
				// if (navItem.loadingType === 'loading') {
				// 	防止重复加载
				// 	return;
				// }

				navItem.loadingType = 'loading';


				// let payStatus = '';
				// let orderFlag = '';
				// let expressStatus = '';
				// let serviceStatus = ''
				// if (index == 1) {
				// 	payStatus = 1;
				// 	serviceStatus = 0
				// } else if (index == 2) {
				// 	payStatus = 1;
				// 	orderFlag = 1;
				// 	expressStatus = 0;
				// } else if (index == 3) {
				// 	payStatus = 1;
				// 	orderFlag = 1;
				// }

				setTimeout(() => {
					let orderList = [];
					let that = this;
					let userId = that.userInfo.data.userId;
					uni.request({
						url: getApp().globalData.url + '/hospital_mini/serviceOrderList',
						method: 'POST',
						data: {
							"createBy": userId,
							// "payStatus": payStatus,
							// "serviceStatus": serviceStatus
						},
						header: {
							'content-type': 'application/x-www-form-urlencoded'
						},
						success: (res) => {
							if (res.data.code == '0') {
								navItem = [];
								orderList = res.data.data;
								console.log("orderList3333", orderList);
								orderList.forEach(item => {
									navItem.orderList.push(item);
								})
							}
						}
					});
					//loaded新字段用于表示数据加载完毕，如果为空可以显示空白页
					this.$set(navItem, 'loaded', true);

					//判断是否还有数据， 有改为 more， 没有改为noMore 
					navItem.loadingType = 'noMore';
				}, 600);
			},

			goToDetail(item) {
				console.log("item", item)
				// uni.navigateTo({
				// 	url:'/pages/order/successOrder2?data=${JSON.stringify(item)}'
				// })
				uni.navigateTo({
					url: `/pages/order/successOrder2?data=${JSON.stringify({
						item: item
					})}`
				})
			},

			//swiper 切换
			changeTab(e) {
				this.tabCurrentIndex = e.target.current;
				console.log("e.target.current", e.target.current)
				this.loadData(this.tabCurrentIndex);
			},
			//顶部tab点击
			tabClick(index) {
				this.tabCurrentIndex = index;
			},
			//评价订单
			evaluateOrder(item) {
				uni.navigateTo({
					url: `/pages/order/evaluate?data=${JSON.stringify({
						item: item
					})}`
				})
			},
			//提交订单
			payOrder(item) {
				uni.navigateTo({
					url: `/pages/money/accountServicepay?data=${JSON.stringify({
						item: item
					})}`
				})
			},

			//删除订单
			deleteOrder(index) {
				uni.showLoading({
					title: '请稍后'
				})
				setTimeout(() => {
					this.navList[this.tabCurrentIndex].orderList.splice(index, 1);
					uni.hideLoading();
				}, 600)
			},
			//取消订单
			cancelOrder(item) {
				uni.showLoading({
					title: '请稍后'
				})
				setTimeout(() => {

					uni.request({
						url: getApp().globalData.url + '/hospital_mini/serviceOrderCancel',
						method: 'POST',
						data: {
							"orderId": item.id
						},
						header: {
							'content-type': 'application/x-www-form-urlencoded'
						},
						success: (res) => {
							if (res.data.code == '0') {
								let {
									stateTip,
									stateTipColor
								} = this.orderStateExp(9);
								item = Object.assign(item, {
									state: 9,
									stateTip,
									stateTipColor,
									payStatus: 2
								})


								let list = this.navList[1].orderList;
								let index = list.findIndex(val => val.id === item.id);
								index !== -1 && list.splice(index, 1);

							}
						}
					});
					uni.hideLoading();
				}, 600)
			},

			//订单状态文字和颜色
			orderStateExp(state) {
				let stateTip = '',
					stateTipColor = '#909399';
				switch (+state) {
					case 1:
						stateTip = '未就诊';
						break;
					case 2:
						stateTip = '就诊中';
						break;
					case 9:
						stateTip = '预约已关闭';
						stateTipColor = '#909399';
						break;

						//更多自定义
				}
				return {
					stateTip,
					stateTipColor
				};
			}
		},
	}
</script>

<style lang="scss">
	page,
	.content {
		background: $page-color-base;
		height: 100%;
	}

	.swiper-box {
		height: calc(100% - 40px);
	}

	.list-scroll-content {
		height: 100%;
	}

	.txt {
		width: 100%;
		// border: 1px solid red;
		font-size: 30rpx;
		// margin: 20rpx;
	}

	.navbar {
		display: flex;
		height: 40px;
		padding: 0 5px;
		background: #fff;
		box-shadow: 0 1px 5px rgba(0, 0, 0, .06);
		position: relative;
		z-index: 10;

		.nav-item {
			flex: 1;
			display: flex;
			justify-content: center;
			align-items: center;
			height: 100%;
			font-size: 15px;
			color: $font-color-dark;
			position: relative;

			&.current {
				color: $base-color;

				&:after {
					content: '';
					position: absolute;
					left: 50%;
					bottom: 0;
					transform: translateX(-50%);
					width: 44px;
					height: 0;
					border-bottom: 2px solid $base-color;
				}
			}
		}
	}

	.uni-swiper-item {
		height: auto;
	}

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
				color: #6c6c6c;
				font-size: 32rpx;
				font-weight: bold;
				flex: 1;
			}

			.state {
				width: 4%;
				height: 40%;
				// color: $base-color;
				// border: 1px solid red;
			}

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
			padding: 20upx 0;

			// border: 1px solid red;
			.goods-img {
				display: block;
				width: 120upx;
				height: 120upx;
				border-radius: 10rpx;
				margin-top: 20rpx;
				// border: 1px solid red;
			}

			.right {
				flex: 1;
				display: flex;
				flex-direction: column;
				padding: 0 30upx 0 24upx;
				overflow: hidden;

				.title {
					font-size: $font-base + 2upx;
					color: $font-color-dark;
					line-height: 1;
					padding: 10rpx 12rpx;
				}

				.attr-box {
					font-size: $font-sm + 2upx;
					color: $font-color-light;
					padding: 10upx 12upx;
				}

				.price {
					margin-top: 10upx;
					font-size: $font-base + 2upx;
					color: $font-color-light;

					&:before {
						content: '';
						font-size: $font-sm;
						margin: 0 2upx 0 8upx;
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
			height: 100upx;
			position: relative;
			padding-right: 30upx;
		}

		.action-btn {
			width: 160upx;
			height: 60upx;
			margin: 0;
			margin-left: 24upx;
			padding: 0;
			text-align: center;
			line-height: 60upx;
			font-size: $font-sm + 2upx;
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
		}
	}


	/* load-more */
	.uni-load-more {
		display: flex;
		flex-direction: row;
		height: 80upx;
		align-items: center;
		justify-content: center
	}

	.uni-load-more__text {
		font-size: 28upx;
		color: #999
	}

	.uni-load-more__img {
		height: 24px;
		width: 24px;
		margin-right: 10px
	}

	.uni-load-more__img>view {
		position: absolute
	}

	.uni-load-more__img>view view {
		width: 6px;
		height: 2px;
		border-top-left-radius: 1px;
		border-bottom-left-radius: 1px;
		background: #999;
		position: absolute;
		opacity: .2;
		transform-origin: 50%;
		animation: load 1.56s ease infinite
	}

	.uni-load-more__img>view view:nth-child(1) {
		transform: rotate(90deg);
		top: 2px;
		left: 9px
	}

	.uni-load-more__img>view view:nth-child(2) {
		transform: rotate(180deg);
		top: 11px;
		right: 0
	}

	.uni-load-more__img>view view:nth-child(3) {
		transform: rotate(270deg);
		bottom: 2px;
		left: 9px
	}

	.uni-load-more__img>view view:nth-child(4) {
		top: 11px;
		left: 0
	}

	.load1,
	.load2,
	.load3 {
		height: 24px;
		width: 24px
	}

	.load2 {
		transform: rotate(30deg)
	}

	.load3 {
		transform: rotate(60deg)
	}

	.load1 view:nth-child(1) {
		animation-delay: 0s
	}

	.load2 view:nth-child(1) {
		animation-delay: .13s
	}

	.load3 view:nth-child(1) {
		animation-delay: .26s
	}

	.load1 view:nth-child(2) {
		animation-delay: .39s
	}

	.load2 view:nth-child(2) {
		animation-delay: .52s
	}

	.load3 view:nth-child(2) {
		animation-delay: .65s
	}

	.load1 view:nth-child(3) {
		animation-delay: .78s
	}

	.load2 view:nth-child(3) {
		animation-delay: .91s
	}

	.load3 view:nth-child(3) {
		animation-delay: 1.04s
	}

	.load1 view:nth-child(4) {
		animation-delay: 1.17s
	}

	.load2 view:nth-child(4) {
		animation-delay: 1.3s
	}

	.load3 view:nth-child(4) {
		animation-delay: 1.43s
	}

	@-webkit-keyframes load {
		0% {
			opacity: 1
		}

		100% {
			opacity: .2
		}
	}
</style>
