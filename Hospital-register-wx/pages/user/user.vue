<template>
	<view class="container">

		<view class="user-section">
			<image class="bg" src="../../static/预约挂号1.jpg" mode="aspectFill" ></image>
			<view class="user-info-box">
				<view class="portrait-box">
					<image class="portrait"
						:src="userInfoS.loginName != null ? '/static/管理员2.png' : '/static/管理员1.png'"></image>
				</view>
				<view class="info-box">
					<text class="username">{{userInfoS.loginName != null ? userInfoS.loginName : "未登录"}}</text>
					<!-- <text class="username">{{userInfoS != null ? userInfoS.userName : '游客'}}</text> -->
				</view>
			</view>
		</view>

		<view class="cover-container" :style="[{
				transform: coverTransform,
				transition: coverTransition
			}]" @touchstart="coverTouchstart" @touchmove="coverTouchmove" @touchend="coverTouchend">
			<image class="arc" src="/static/arc.png"></image>


			<!-- 订单 -->
			<!-- <view class="order-section">
				<view class="order-item" @click="navTo('/pages/order/serviceorder?state=0')" hover-class="common-hover"  :hover-stay-time="50">
					<text class="yticon icon-shouye"></text>
					<text>挂号记录</text>
				</view>
				<view class="order-item" @click="navTo('/pages/order/serviceorder?state=1')"  hover-class="common-hover" :hover-stay-time="50">
					<text class="yticon icon-daifukuan"></text>
					<text>待就诊</text>
				</view>
			</view> -->
			<!-- 浏览历史 -->
			<view class="history-section icon">
				<!-- <list-cell icon="icon-erjiye-yucunkuan" iconColor="#54b4ef" title="挂号记录" @eventClick="navTo('/pages/order/serviceorder')"></list-cell> -->
				<!-- <list-cell icon="/static/挂号记录.png" iconColor="#54b4ef" title="挂号记录"
					@eventClick="navTo('/pages/record/record')"></list-cell> -->
				<view class="history-section1 " @click="goToDetail()">
					<view class="kefu" style="margin-left: 10rpx;">
						<img src="/static/挂号记录.png" alt="">
						<view class="txt">挂号记录</view>
					</view>
					<view class="phone" style="padding-left: 200rpx;">
						<img src="/static/go.png" alt="">
					</view>
					<!-- <text style="font-size: 25rpx;">124837583</text> -->
				</view>
				<view class="history-section1 " @click="goToMine()">
					<view class="kefu" style="margin-left: 10rpx;">
						<img src="/static/个人中心.png" alt="">
						<view class="txt">个人中心</view>
					</view>
					<view class="phone" style="padding-left: 200rpx;">
						<img src="/static/go.png" alt="">
					</view>
					<!-- <text style="font-size: 25rpx;">124837583</text> -->
				</view>
				<!-- <list-cell icon="icon-erjiye-yucunkuan" iconColor="#54b4ef" title="个人中心" @eventClick="navTo('/pages/account/account')"></list-cell> -->
				<!-- <list-cell icon="icon-erjiye-yucunkuan" iconColor="#54b4ef" title="个人中心"
					@eventClick="navTo('/pages/mine/mine')"></list-cell> -->
				<!-- <list-cell icon="icon-dizhi" iconColor="#54b4ef" title="就诊人信息" @eventClick="navTo('/pages/address/address')"></list-cell>
				<list-cell icon="icon-shezhi1" iconColor="#FFC125" title="设置" @eventClick="navTo('/pages/set/set')"></list-cell> -->
			</view>
			<!-- <view class="history-section icon">
				<list-cell icon="icon-erjiye-yucunkuan" iconColor="#54b4ef" title="客服电话"
					@eventClick="navTo('/pages/record/record')"></list-cell>
			</view> -->

		</view>

		<view class="history-section1 ">
			<view class="kefu">
				<img src="/static/客服.png" alt="">
				<view class="txt">客服电话</view>
			</view>
			<view class="phone">
				0771-1587727
			</view>
			<!-- <text style="font-size: 25rpx;">124837583</text> -->
		</view>
	</view>
</template>
<script>
	import listCell from '@/components/mix-list-cell';
	import {
		mapState
	} from 'vuex';
	let startY = 0,
		moveY = 0,
		pageAtTop = true;
	export default {
		components: {
			listCell
		},
		data() {
			return {
				coverTransform: 'translateY(0px)',
				coverTransition: '0s',
				moving: false,
				userInfoS: {}
			}
		},
		async onLoad() {

			this.userInfoS = this.userInfo.data;
			// if (this.userInfo.data == undefined ) {
			// 	console.log("444")
			// 	this.userInfoS = null
			// 	console.log("777",this.userInfoS)
			// } else{
			// 	console.log("555")
			// 	this.userInfoS = this.userInfo.data;
			// 	console.log("666",this.userInfoS)
			// }
			this.getNearServiceorderData();
			console.log("2222", this.userInfo.data);
		},
		// #ifndef MP
		onNavigationBarButtonTap(e) {
			const index = e.index;
			if (index === 0) {
				this.navTo('/pages/set/set');
			} else if (index === 1) {
				// #ifdef APP-PLUS
				const pages = getCurrentPages();
				const page = pages[pages.length - 1];
				const currentWebview = page.$getAppWebview();
				currentWebview.hideTitleNViewButtonRedDot({
					index
				});
				// #endif
				uni.navigateTo({
					url: '/pages/notice/notice'
				})
			}
		},
		// #endif
		computed: {
			...mapState(['hasLogin']),
			...mapState(['userInfo'])
		},
		methods: {
			goToDetail() {
				if (!this.hasLogin) {
					uni.navigateTo({
						url: `/pages/public/login`
					})
					//  uni.navigateTo({
					// 	url: `/pages/record/record`
					// })
				} else {
					uni.navigateTo({
						url: '/pages/record/record'
					})
				}

			},
			goToMine() {
				if (!this.hasLogin) {
					uni.navigateTo({
						url: `/pages/public/login`
					})
				} else {
					uni.navigateTo({
						url: '/pages/mine/mine'
					})
				}

			},
			getNearServiceorderData() {
				if (this.hasLogin) {
					let userId = this.userInfo.data.userId;
					console.log("userid",userId)
					uni.request({
						url: getApp().globalData.url + '/hospital_mini/getNearServiceorderData',
						method: 'POST',
						data: {
							"createBy": userId

						},
						header: {
							'content-type': 'application/x-www-form-urlencoded'
						},
						success: (res) => {
							console.log("999999", res.data)
							console.log("999999", res.data.data)
							if (res.data.code == '0') {
								// let servicedata = res.data.data;
								// let orderDate = servicedata.orderDate;
								// let orderTime = servicedata.orderTime;
								// let registerName = servicedata.registerName;
								// let serverName = servicedata.serverName;
								// let typeInfo = servicedata.typeInfo;
								// let showMsg = '您好，' + orderDate + '的' + orderTime + ',' + '您预约了' +
								// 	registerName + '医生的' + serverName + '（' + typeInfo + '）就诊，请不要忘记！';
								uni.showToast({
									title: res.data.msg,
									icon: 'none',
									mask: true,
									duration: 3000
								});
							}
							// } else if (res.data.data.serviceStatus == '4') {
							// 	let servicedata = res.data.data;
							// 	let orderDate = servicedata.orderDate;
							// 	let orderTime = servicedata.orderTime;
							// 	let registerName = servicedata.registerName;
							// 	let serverName = servicedata.serverName;
							// 	let typeInfo = servicedata.typeInfo;
							// 	let showMsg = '您好，' + orderDate + '的' + orderTime + ',' + '您预约了' +
							// 		registerName + '医生的' + serverName + '（' + typeInfo +
							// 		'）就诊已过期，请尽快前往医院联系医生调时间就诊！';
							// 	uni.showToast({
							// 		title: showMsg,
							// 		icon: 'none',
							// 		mask: true,
							// 		duration: 2000
							// 	});
							// } else {

							// }
						}
					});




				}
			},
			/**
			 * 统一跳转接口,拦截未登录路由
			 * navigator标签现在默认没有转场动画，所以用view
			 */
			navTo(url) {
				if (!this.hasLogin) {
					url = '/pages/public/login';
				}
				uni.navigateTo({
					url
				})
			},

			/**
			 *  会员卡下拉和回弹
			 *  1.关闭bounce避免ios端下拉冲突
			 *  2.由于touchmove事件的缺陷（以前做小程序就遇到，比如20跳到40，h5反而好很多），下拉的时候会有掉帧的感觉
			 *    transition设置0.1秒延迟，让css来过渡这段空窗期
			 *  3.回弹效果可修改曲线值来调整效果，推荐一个好用的bezier生成工具 http://cubic-bezier.com/
			 */
			coverTouchstart(e) {
				if (pageAtTop === false) {
					return;
				}
				this.coverTransition = 'transform .1s linear';
				startY = e.touches[0].clientY;
			},
			coverTouchmove(e) {
				moveY = e.touches[0].clientY;
				let moveDistance = moveY - startY;
				if (moveDistance < 0) {
					this.moving = false;
					return;
				}
				this.moving = true;
				if (moveDistance >= 80 && moveDistance < 100) {
					moveDistance = 80;
				}

				if (moveDistance > 0 && moveDistance <= 80) {
					this.coverTransform = `translateY(${moveDistance}px)`;
				}
			},
			coverTouchend() {
				if (this.moving === false) {
					return;
				}
				this.moving = false;
				this.coverTransition = 'transform 0.3s cubic-bezier(.21,1.93,.53,.64)';
				this.coverTransform = 'translateY(0px)';
			}
		}
	}
</script>
<style lang='scss'>
	%flex-center {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}

	%section {
		display: flex;
		justify-content: space-around;
		align-content: center;
		background: #fff;
		border-radius: 10upx;
	}

	.history-section1 {
		display: flex;
		width: 100%;
		/* height: 50rpx; */
		/* border: 1px solid green; */
		padding: 20rpx;
		font-size: 28rpx;
		color: #303133;

		.kefu {

			width: 60%;
			height: 50rpx;
			display: flex;
			/* border: 1px solid red; */
			margin-left: 30rpx;

			img {
				/* border: 1px solid red; */
				width: 45rpx;
				height: 45rpx;
			}

			.txt {
				width: 50%;
				height: 50rpx;
				line-height: 50rpx;
				margin-left: 20rpx;
				/* border: 1px solid red; */

			}
		}

		.phone {
			width: 40%;
			text-align: center;
			height: 50rpx;
			line-height: 50rpx;

			img {
				width: 45rpx;
				height: 45rpx;
			}

			/* border: 1px solid yellow; */
		}

	}


	.user-section {
		height: 520upx;
		padding: 100upx 30upx 0;
		position: relative;

		.bg {
			position: absolute;
			left: 0;
			top: 0;
			width: 100%;
			height: 100%;
			filter: blur(1px);
			background-color: #55aaff;
			/* opacity: .7; */
		}
	}

	.user-info-box {
		height: 180upx;
		display: flex;
		align-items: center;
		position: relative;
		z-index: 1;

		.portrait {
			width: 100upx;
			height: 100upx;
			/* border: 5upx solid #fff; */
			/* border-radius: 50%; */
		}

		.username {
			font-size: $font-lg + 6upx;
			color: $font-color-dark;
			margin-left: 20upx;
		}
	}

	.vip-card-box {
		display: flex;
		flex-direction: column;
		color: #f7d680;
		height: 240upx;
		background: linear-gradient(left, rgba(0, 0, 0, .7), rgba(0, 0, 0, .8));
		border-radius: 16upx 16upx 0 0;
		overflow: hidden;
		position: relative;
		padding: 20upx 24upx;

		.card-bg {
			position: absolute;
			top: 20upx;
			right: 0;
			width: 380upx;
			height: 260upx;
		}

		.b-btn {
			position: absolute;
			right: 20upx;
			top: 16upx;
			width: 132upx;
			height: 40upx;
			text-align: center;
			line-height: 40upx;
			font-size: 22upx;
			color: #36343c;
			border-radius: 20px;
			background: linear-gradient(left, #f9e6af, #ffd465);
			z-index: 1;
		}

		.tit {
			font-size: $font-base+2upx;
			color: #f7d680;
			margin-bottom: 28upx;

			.yticon {
				color: #f6e5a3;
				margin-right: 16upx;
			}
		}

		.e-b {
			font-size: $font-sm;
			color: #d8cba9;
			margin-top: 10upx;
		}
	}

	.cover-container {
		background: $page-color-base;
		margin-top: -150upx;
		padding: 0 30upx;
		position: relative;
		background: #f5f5f5;
		padding-bottom: 20upx;

		.arc {
			position: absolute;
			left: 0;
			top: -34upx;
			width: 100%;
			height: 36upx;
		}
	}

	.tj-sction {
		@extend %section;

		.tj-item {
			@extend %flex-center;
			flex-direction: column;
			height: 140upx;
			font-size: $font-sm;
			color: #75787d;
		}

		.num {
			font-size: $font-lg;
			color: $font-color-dark;
			margin-bottom: 8upx;
		}
	}

	.order-section {
		@extend %section;
		padding: 28upx 0;
		margin-top: 20upx;

		.order-item {
			@extend %flex-center;
			width: 120upx;
			height: 120upx;
			border-radius: 10upx;
			font-size: $font-sm;
			color: $font-color-dark;
		}

		.yticon {
			font-size: 48upx;
			margin-bottom: 18upx;
			color: #fa436a;
		}

		.icon-shouhoutuikuan {
			font-size: 44upx;
		}
	}

	.history-section {
		padding: 30upx 0 0;
		margin-top: 20upx;
		background: #fff;
		border-radius: 10upx;

		.sec-header {
			display: flex;
			align-items: center;
			font-size: $font-base;
			color: $font-color-dark;
			line-height: 40upx;
			margin-left: 30upx;

			.yticon {
				font-size: 44upx;
				color: #5eba8f;
				margin-right: 16upx;
				line-height: 40upx;
			}
		}

		.h-list {
			white-space: nowrap;
			padding: 30upx 30upx 0;

			image {
				display: inline-block;
				width: 160upx;
				height: 160upx;
				margin-right: 20upx;
				border-radius: 10upx;
			}
		}
	}
</style>
