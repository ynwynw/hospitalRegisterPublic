<template>
	<view class="">
		<view class="success_msg" v-if="data.serviceStatus == 0">
			<view class="success">
				预约成功
			</view>
			<view class="msg">
				您已预约成功，请合理安排以免过期哦
			</view>
		</view>
		<view class="success_msg" v-else-if="data.serviceStatus == 2" style="background-color: #14cc61;">
			<view class="success">
				就诊完成
			</view>
			<view class="msg">
				您已就诊完成，请注意身体健康，多喝热水
			</view>
		</view>
		<view class="success_msg" v-else-if="data.serviceStatus == 1" style="background-color: #ccc;">
			<view class="success">
				已取消
			</view>
			<view class="msg">
				当前预约-已取消
			</view>
		</view>
		<view class="success_msg" v-else-if="data.serviceStatus == 3" style="background-color: #ccc;">
			<view class="success">
				已过期
			</view>
			<view class="msg">
				当前预约-已过期
			</view>
		</view>

		<view class="base_info">
			<view class="base">
				预约信息
			</view>
			<view class="info">
				<view class="text1">就诊患者</view>
				<view class="text2">{{data.contactName}}</view>
			</view>
			<view class="info">
				<view class="text1">手机号</view>
				<view class="text2">{{data.contactPhone}}</view>
			</view>
			<view class="info">
				<view class="text1">就诊医院</view>
				<view class="text2">{{baseInfo.name}}</view>
			</view>
			<view class="info">
				<view class="text1">就诊科室</view>
				<view class="text2">{{data.registerName}}</view>
			</view>
			<view class="info">
				<view class="text1">医生姓名</view>
				<view class="text2">{{data.serviceinfo.name}}</view>
			</view>
			<view class="info">
				<view class="text1">就诊时间</view>
				<view class="text2">{{data.orderDate}} {{data.orderTime}}</view>
			</view>
			<view class="info">
				<view class="text1">取号地点</view>
				<view class="text2">{{baseInfo.address}}</view>
			</view>
			<view class="info">
				<view class="text1">医事服务费</view>
				<view class="text2">{{data.serviceinfo.price}}元</view>
			</view>
			<view class="info">
				<view class="text1">疾病描述</view>
				<view class="text2">
					{{data.illTroditional != null && data.illTroditional != '' ? data.illTroditional : '无'}}</view>
			</view>
			<view class="tip">
				<view class="text1" style="color: #000;font-size: 30rpx;">温馨提示</view>
				<view class="text2" style="font-size: 25rpx;">医师服务费均在就诊时由医院收取本平台不收取任何费用</view>
			</view>

			<view class="troditional">
				<view class="text1">医生介绍</view>
				<view class="text2">{{data.serviceinfo.introduce}}</view>
			</view>



		</view>
		<view class="button-container" v-if="data.serviceStatus == 0">
			<button @click="goToHome()">取消</button>
		</view>
		<view class="button-container" v-else-if="data.serviceStatus == 1 || data.serviceStatus == 3">
			<button @click="goToRegister()">再次预约</button>
		</view>
		<view class="button-container" v-else-if="data.serviceStatus == 2 && data.evaluatePoint == null">
			<button @click="goToEvaluate()">评价一下</button>
		</view>
		<view class="button-container" v-else-if="data.serviceStatus == 2 && data.evaluatePoint != null">
			<button @click="goToEvaluate()">已评价</button>
		</view>
		<view class="popup-container" v-show="show">
			<view class="popup-box">
				<view class="popup-title">{{ title }}</view>
				<view class="popup-input">
					<input v-model="inputValue" placeholder="请填写您的取消原因与问题" />
				</view>
				<view class="popup-btns">
					<button class="popup-btn" @click="confirm">提交</button>
					<button class="popup-btn" @click="cancel">再想想</button>

				</view>
			</view>
			<view class="popup-mask" v-show="show"></view>
		</view>
	</view>
</template>

<script>
	export default {
		props: {
			title: {
				type: String,
				default: "取消原因",
			},
		},
		data() {
			return {
				data: '',
				show: false,
				inputValue: "",
				registerInfo: '',
				baseInfo: '',
				inputValue: ''

			}
		},
		onLoad: function(option) {
			this.close()
			this.data = JSON.parse(option.data).item;
			this.getRegister(this.data.registerId)
			console.log("data", this.data);
			console.log("createTime", this.data.serviceinfo.name)
			this.getBaseInfo()
		},
		methods: {
			getBaseInfo() {
				let hospitalId = this.data.hospitalId
				console.log("hospitalId",hospitalId)
				uni.request({
					url: getApp().globalData.url + '/hospital_mini/getBaseInfo',
					method: 'POST',
					data: {
						"hospitalId":hospitalId
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						this.baseInfo = res.data.data
						console.log("this.getBaseInfo", this.baseInfo)
					}
				})
			},
			getRegister(id) {
				uni.request({
					url: getApp().globalData.url + '/hospital_mini/getRegisterInfoList',
					method: 'POST',
					data: {
						"id": id,
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						this.registerInfo = res.data.data
						console.log("this.registerInfo", this.registerInfo)
					}
				})
			},
			goToEvaluate() {
				uni.navigateTo({
					url: `/pages/order/evaluate?data=${JSON.stringify({
						data: this.data
					})}`
				})
			},
			goToRegister() {
				uni.navigateTo({
					url: `/pages/detail/detail?data=${JSON.stringify({
						data: this.data
					})}`
				})
			},
			goToHome() {
				this.open()

			},
			open() {
				this.show = true;
			},
			close() {
				this.show = false;
				this.inputValue = "";
			},
			confirm() {
				console.log("this.inputValue1", this.inputValue)
				let reasonCancel = this.inputValue
				console.log("this.inputValue2", reasonCancel)
				if (this.inputValue.trim() === "") {
					uni.showToast({
						title: "请填写取消预约原因",
						icon: "none",
					});
				} else {
					this.close();
					uni.showToast({
						title: "已取消",
						icon: "none",
					});
					uni.request({
						url: getApp().globalData.url + '/hospital_mini/serviceOrderCancel',
						method: 'POST',
						data: {
							"orderId": this.data.id,
							"reasonCancel": reasonCancel
						},
						header: {
							'content-type': 'application/x-www-form-urlencoded'
						},
						success: (res) => {
							this.data = res.data.data
						}
					})
					uni.navigateTo({
						url: '/pages/record/record'
					})
				}
			},
			cancel() {
				this.close();
			}
		},

	}
</script>

<style lang="scss">
	page {
		background: #ebebeb;
		height: 100%;
	}


	.popup-container {
		position: fixed;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		z-index: 999;
		display: flex;
		justify-content: center;
		align-items: center;

		.popup-box {
			width: 80%;
			max-width: 600rpx;
			background-color: #fff;
			border-radius: 8rpx;
			box-shadow: 0 0 20rpx rgba(0, 0, 0, 0.1);
			padding: 30rpx;

			.popup-title {
				font-size: 32rpx;
				font-weight: bold;
				text-align: center;
				margin-bottom: 30rpx;
			}

			.popup-input {
				border: 1rpx solid #ccc;
				border-radius: 4rpx;
				padding: 10rpx;
				margin-bottom: 30rpx;
				background-color: #fafafa;

				input {
					width: 100%;
					font-size: 28rpx;
					border: none;
					outline: none;
					padding-left: 20rpx;

				}
			}

			.popup-btns {
				display: flex;
				justify-content: space-between;
				align-items: center;

				.popup-btn {
					width: 45%;
					height: 80rpx;
					line-height: 80rpx;
					text-align: center;
					font-size: 32rpx;
					font-weight: bold;
					color: #6d6d6d;
					// border: 1rpx solid #007aff;
					background-color: #fff;
					border: none;
				}
			}
		}

		.popup-mask {
			position: absolute;
			top: 0;
			left: 0;
			right: 0;
			bottom: 0;
			background-color: rgba(0, 0, 0, 0.4);
			z-index: -1;
			transition: all 0.3s ease-in-out;

			&.show {
				z-index: 998;
			}
		}
	}

	.success_msg {
		display: block;
		width: 100%;
		height: 300rpx;
		// border: 1px solid red;
		// text-align: center;
		// line-height: 250rpx;
		background-color: skyblue;
		padding-top: 50rpx;

		.success {
			// margin: 200rpx;
			// border: 1px solid green;
			width: 100%;
			text-align: center;
			font-size: 35rpx;
			font-weight: bold;
			color: #fff;
			// margin-top: 50rpx;

		}

		.msg {
			font-size: 30rpx;
			// font-weight: bold;
			color: #fff;
			// border: 1px solid purple;
			width: 100%;
			text-align: center;
			margin-top: 50rpx;
		}
	}

	.base_info {
		width: 100%;
		// background-color: #aaff7f;
		padding: 20rpx;

		.base {
			width: 100%;
			height: 60rpx;
			// border: 1px solid red;
			font-size: 35rpx;
			font-weight: bold;
			padding-left: 20rpx;
			padding-right: 20rpx;
			line-height: 60rpx;
			background-color: #fff;
		}

		.info {
			display: flex;
			width: 100%;
			padding-left: 20rpx;
			padding-right: 20rpx;
			line-height: 60rpx;
			background-color: #fff;
			// border: 1px solid red;

			.text1 {
				font-size: 30rpx;
				// width: 50%;
				color: #585858;
				width: 40%;
				// border: 1px solid red;

			}

			.text2 {
				font-size: 30rpx;
				width: 60%;
				color: #585858;
				// border: 1px solid red;
			}
		}

		.tip {
			color: blue;
			font-size: 25rpx;
			padding: 20rpx;
			width: 100%;
			background-color: #fff;
		}

		.troditional {
			width: 100%;
			padding: 20rpx;
			font-weight: bold;
			font-size: 25rpx;
			background-color: #fff;
		}




	}

	.button-container {
		width: 100%;
		height: 200rpx;
		margin-top: 60rpx;
		margin-bottom: 60rpx;
		text-align: center;

		// line-height: 200rpx;
		// border: 1px solid red;
		button {
			width: 50%;
			background-color: #00caca;
			border: none;
			color: white;
			// padding: 10px 20px;
			border-radius: 20px;
			font-size: 16px;
			cursor: pointer;
		}
	}
</style>
