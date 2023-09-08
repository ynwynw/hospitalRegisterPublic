<template>
	<view class="content b-t">
		<view class="order-item" v-for="(item, index) in serviceList" :key="index">
			<view class="goods-box-single" @tap="goChooseDatePage(item)">
				<image class="goods-img" :src="urlKey+item.logo" mode="aspectFill"></image>
				<view class="right">
					<text class="title clamp">{{item.name}}--￥{{item.price}}</text>
					<text
						class="attr-box">{{item.introduce.length>limit ?  item.introduce.slice(0, limit) + '...' : item.introduce}}</text>
					<view class="down">
						<text class="price">{{item.typeInfo}}</text>
						<view class="action1">
							<view class="action-btn1" @click="goChooseDatePage(item)">预约</view>

						</view>
					</view>

				</view>
			</view>
			<!-- <view class="action-box b-t">
				<view class="action-btn recom" @click="goChooseDatePage(item)">预约</view>
			</view> -->
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
				source: 0,
				serviceList: [],
				urlKey: getApp().globalData.url + "/",
				registerData: {},
				limit: 22,
			}
		},
		computed: {
			...mapState(['hasLogin', 'userInfo'])
		},
		onLoad(option) {
			this.registerData = JSON.parse(option.data)
			this.getServiceList();
		},
		methods: {
			goChooseDatePage(item) {
				console.log("item222",item)
				uni.navigateTo({
					url: `/pages/detail/detail?register=${JSON.stringify(this.registerData)}&service=${JSON.stringify(item)}`
				})
			},

			getServiceList() {
				let that = this;
				let registerId = that.registerData.id;
				uni.request({
					url: getApp().globalData.url + '/hospital_mini/getServiceList',
					method: 'POST',
					data: {
						"registerId": registerId
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						if (res.data.code == '0') {
							that.serviceList = res.data.data;
							console.log("res.data.data", res.data.data)
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
		background: #f5f5f5;
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
				width: 150upx;
				height: 150upx;
				margin-top: 24upx;
			}

			.right {
				flex: 1;
				display: flex;
				flex-direction: column;
				padding: 30upx 30upx 0 24upx;
				overflow: hidden;

				.title {
					font-size: $font-base + 8upx;
					color: #b36d41;
					line-height: 1;
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
</style>
