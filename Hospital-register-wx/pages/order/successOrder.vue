<template>
	<div class="success-page">
		<div class="yes">
			<!-- <div class="checkmark_circle"></div>
			<div class="checkmark_stem"></div>
			<div class="checkmark_kick"></div> -->
			<img src="/static/yes.png" alt="">
		</div>
		<div class="success-message">
			<view class="view1">预约成功！</view>
			<view class="view2">可在挂号记录查看详情</view>
		</div>
		<div class="button-container">
			<button @click="goToHome()">返回首页</button>
		</div>
	</div>
</template>

<script>
	export default {
		methods: {
			goToHome() {
				// uni.navigateTo({
				// 	url: '/pages/index//index',
					
				// });
				console.log("222")
				uni.switchTab({
					url: '/pages/index/index'
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	.success-page {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		height: 100vh;

		.yes {
			height: 300rpx;
			width: 300rpx;
			// border: 1px solid red;

			img {
				width: 300rpx;
				height: 300rpx;
				// box-shadow: 4rpx #4caf50;
				// border: 1px solid green;
			}
		}
	}

	.checkmark {
		width: 56px;
		height: 56px;
		border-radius: 50%;
		display: flex;
		align-items: center;
		justify-content: center;
		background-color: #4caf50;
	}

	.checkmark_circle {
		width: 50px;
		height: 50px;
		border-radius: 50%;
		background-color: white;
		display: inline-block;
		transform: rotate(45deg);
	}

	.checkmark_stem {
		width: 3px;
		height: 25px;
		background-color: white;
		display: inline-block;
		transform: rotate(-45deg);
	}

	.checkmark_kick {
		width: 3px;
		height: 13px;
		background-color: white;
		display: inline-block;
		transform: rotate(-45deg);
	}

	.success-message {
		margin-top: 20px;
		text-align: center;

		.view1 {
			font-weight: bold;
			font-size: 25rpx
		}

		.view2 {
			margin-top: 10rpx;
			color: #767676;
			font-size: 30rpx
		}
	}

	// .success-message h2 {
	// 	font-size: 24px;
	// 	font-weight: bold;
	// 	margin-bottom: 10px;
	// }

	// .success-message p {
	// 	font-size: 16px;
	// 	color: #999;
	// }

	.button-container {
		width: 50%;
		margin-top: 60rpx;
		// border: 1px solid red;
	}

	button {
		background-color: #00caca;
		border: none;
		color: white;
		// padding: 10px 20px;
		border-radius: 20px;
		font-size: 16px;
		cursor: pointer;
	}
</style>
