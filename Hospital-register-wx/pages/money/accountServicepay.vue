<template>
	<view class="app">
		<view class="price-box">
			<text>支付金额</text>
			<text class="price">{{orderInfo.item.price}}</text>
		</view>

		<view class="pay-type-list">

			<view class="type-item b-b" @click="changePayType(1)">
				<text class="icon yticon icon-erjiye-yucunkuan"></text>
				<view class="con">
					<text class="tit">余额支付</text>
					<text>剩余余额：￥{{accountData.remainingSum}}</text>
				</view>
				<label class="radio">
					<radio value="" color="#fa436a" :checked='payType == 1' />
					</radio>
				</label>
			</view>
		</view>
		
		<text class="mix-btn" @click="confirm">确认支付</text>
	</view>
</template>

<script>
	import {
	    mapState 
	} from 'vuex';
	export default {
		data() {
			return {
				payType: 1,
				orderInfo: {},
				accountData:''
			};
		},
		computed: {
			...mapState(['userInfo'])
		},
		onLoad(options) {
			let order = JSON.parse(options.data);
			this.orderInfo = order;
			console.log(order);
			this.loadData();
		},

		methods: {
			//请求数据
			loadData(){
				let that = this;
				let userId = that.userInfo.data.userId;
				uni.request({
					url: getApp().globalData.url + '/hospital_mini/getMyAccount',
					method: 'POST',
					data: {
						"userId":userId
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						if(res.data.code == '0'){
							that.accountData = res.data.data;
						}
					}
				});
			},
			//选择支付方式
			changePayType(type) {
				this.payType = type;
			},
			//确认支付
			confirm: async function() {
				let orderId = this.orderInfo.item.id;
				let accountId = this.accountData.id;
				let price = this.orderInfo.item.price;
				let account = this.accountData.remainingSum;
				if(price > account){
					this.$api.msg('余额不足请前去充值');
					return;
				}
				uni.request({
					url: getApp().globalData.url + '/hospital_mini/serviceOrderPay',
					method: 'POST',
					data: {
						"orderId":orderId,
						"accountId":accountId
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						uni.redirectTo({
							url: '/pages/money/payServiceSuccess'
						})
					}
				});
				
			},
		}
	}
</script>

<style lang='scss'>
	.app {
		width: 100%;
	}

	.price-box {
		background-color: #fff;
		height: 265upx;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		font-size: 28upx;
		color: #909399;

		.price{
			font-size: 50upx;
			color: #303133;
			margin-top: 12upx;
			&:before{
				content: '￥';
				font-size: 40upx;
			}
		}
	}

	.pay-type-list {
		margin-top: 20upx;
		background-color: #fff;
		padding-left: 60upx;
		
		.type-item{
			height: 120upx;
			padding: 20upx 0;
			display: flex;
			justify-content: space-between;
			align-items: center;
			padding-right: 60upx;
			font-size: 30upx;
			position:relative;
		}
		
		.icon{
			width: 100upx;
			font-size: 52upx;
		}
		.icon-erjiye-yucunkuan {
			color: #fe8e2e;
		}
		.icon-weixinzhifu {
			color: #36cb59;
		}
		.icon-alipay {
			color: #01aaef;
		}
		.tit{
			font-size: $font-lg;
			color: $font-color-dark;
			margin-bottom: 4upx;
		}
		.con{
			flex: 1;
			display: flex;
			flex-direction: column;
			font-size: $font-sm;
			color: $font-color-light;
		}
	}
	.mix-btn {
		display: flex;
		align-items: center;
		justify-content: center;
		width: 630upx;
		height: 80upx;
		margin: 80upx auto 30upx;
		font-size: $font-lg;
		color: #fff;
		background-color: $base-color;
		border-radius: 10upx;
		box-shadow: 1px 2px 5px rgba(219, 63, 96, 0.4);
	}

</style>
