<template>
	<view class="container">

		<view class="credit-section">
			<image class="bg" src="/static/credit-bg.png"></image>
		</view>

		<view class="credit-score">
			评价<text>{{data.data.serverName}}</text>医生
			<view class="credit-icon">
				<uni-rate size="30" :value="pointLevel" @change="changePoint"></uni-rate>
			</view>
		</view>

		<view class="row b-b" style="margin-top: 30rpx;">
			<text class="tit">建议或意见</text>
			<input class="input" type="text" :value="remark" placeholder="具体的建议内容" placeholder-class="placeholder"
				@change="changeRemark" />
		</view>

		<view class="credit-score">

		</view>

		<view class="action-btn-group">
			<button type="primary" class=" action-btn no-border add-cart-btn" @click="subPoint()">提交评价</button>
		</view>

	</view>
</template>
<script>
	import uniRate from '@/components/uni-rate/uni-rate.vue'
	import {
		mapState
	} from 'vuex';
	export default {
		components: {
			uniRate
		},
		data() {
			return {
				pointLevel: 0,
				pointDisable: false,
				data: {},
				remark: ""
			};
		},
		computed: {
			...mapState(['userInfo'])
		},
		watch: {

		},
		onLoad(options) {
			this.data = JSON.parse(options.data);
			console.log("data1111", this.data.data.serverName)
		},
		methods: {
			//请求数据
			subPoint() {
				let that = this;
				if (that.pointLevel == null || that.remark == null) {
					uni.showModal({
						title: '提示',
						showCancel: false,
						content: '请先填写评价内容或者评分才能提交哦!',
						success: function(res) {
							uni.reLaunch({
								url: `/pages/record/record`
							})
						}
					});
				} else {
					uni.request({

						url: getApp().globalData.url + '/hospital_mini/serviceOrderComment',
						method: 'POST',
						data: {
							"orderId": this.data.data.id,
							"point": that.pointLevel,
							"remark": that.remark
						},
						header: {
							'content-type': 'application/x-www-form-urlencoded'
						},
						success: (res) => {
							uni.showModal({
								title: '提示',
								showCancel: false,
								content: '已提交!',
								success: function(res) {
									uni.reLaunch({
										url: `/pages/record/record`
									})
								}
							});
						}

					});
				}
			},
			changePoint(e) {
				this.pointLevel = e.value;
			},
			changeRemark(e) {
				this.remark = e.detail.value;
			}
		}
	}
</script>
<style lang='scss'>
	.credit-section {
		height: 200upx;
		position: relative;

		.bg {
			position: absolute;
			left: 0;
			top: 0;
			width: 100%;
			height: 100%;
			filter: blur(1px);
			opacity: .7;
		}
	}

	.credit-score {

		margin-top: 100upx;
		margin-left: 20upx;
		font-size: 30rpx;

		text {
			color: #6f6f6f;
			font-weight: bold;
		}

		.credit-icon {
			padding-top: 100upx;
			padding-left: 100upx;
		}
	}

	textarea {
		height: 190upx;
	}

	.row {
		display: flex;
		align-items: center;
		position: relative;
		padding: 0 30upx;
		height: 110upx;
		background: #fff;

		.tit {
			flex-shrink: 0;
			width: 150upx;
			font-size: 30upx;
			color: $font-color-dark;
		}

		.input {
			flex: 1;
			font-size: 30upx;
			color: $font-color-dark;
		}

		.icon-shouhuodizhi {
			font-size: 36upx;
			color: $font-color-light;
		}
	}

	.action-btn-group {
		display: flex;
		height: 76upx;
		border-radius: 100px;
		overflow: hidden;
		box-shadow: 0 20upx 40upx -16upx #fa436a;
		box-shadow: 1px 2px 5px rgba(219, 63, 96, 0.4);
		background: linear-gradient(to right, #ffac30, #fa436a, #F56C6C);
		align-items: center;
		position: relative;

		&:after {
			content: '';
			position: absolute;
			top: 50%;
			right: 50%;
			transform: translateY(-50%);
			height: 28upx;
			width: 0;
			border-right: 1px solid rgba(255, 255, 255, .5);
		}

		.action-btn {
			display: flex;
			align-items: center;
			justify-content: center;
			width: 180upx;
			height: 100%;
			font-size: $font-base;
			padding: 0;
			border-radius: 0;
			background: transparent;
		}
	}
</style>
