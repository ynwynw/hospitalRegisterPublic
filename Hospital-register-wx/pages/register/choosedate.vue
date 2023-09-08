<template>
	<view class="content b-t">
		<view>
		  <wuc-tab :tab-list="dateArr" :tabCur.sync="TabCur" tab-class="text-center bg-white wuc-tab fixed" select-class="text-blue" @change="tabChange"></wuc-tab>
		  <view class="cu-bar bg-white solid-bottom">
		   
		  </view>
		  <!-- <view class="bg-white padding margin text-center text-black">{{dateArr[TabCur].week}}-{{dateArr[TabCur].month}}{{dateArr[TabCur].date}}</view> -->
		</view>
		
		<view style="margin-top: 100upx;width: 100%;text-align: center;">
			<view>
				<button type="default" plain="true" size="mini" :disabled="canClickOne" :style="{'background-color' : btnOneColor}" @click="goOrder(10)"><text class="yticon icon-naozhong"></text>10:00</button>
			</view>
			<view style="margin-top: 50upx;">
				<button type="default" plain="true" size="mini" :disabled="canClickTwo" :style="{'background-color' : btnTwoColor}" @click="goOrder(12)"><text class="yticon icon-naozhong"></text>12:00</button>
			</view>
			<view style="margin-top: 50upx;">
				<button type="default" plain="true" size="mini" :disabled="canClickThree" :style="{'background-color' : btnThreeColor}" @click="goOrder(14)"><text class="yticon icon-naozhong"></text>14:00</button>
			</view>
			<view style="margin-top: 50upx;">
				<button type="default" plain="true" size="mini" :disabled="canClickFour" :style="{'background-color' : btnFourColor}" @click="goOrder(16)"><text class="yticon icon-naozhong"></text>16:00</button>
			</view>
			<view style="margin-top: 50upx;">
				<button type="default" plain="true" size="mini" :disabled="canClickFive" :style="{'background-color' : btnFiveColor}" @click="goOrder(18)"><text class="yticon icon-naozhong"></text>18:00</button>
			</view>
			<view style="margin-top: 50upx;">
				<button type="default" plain="true" size="mini" :disabled="canClickSix" :style="{'background-color' : btnSixColor}" @click="goOrder(20)"><text class="yticon icon-naozhong"></text>20:00</button>
			</view>
			
		</view>
		
		<view class="page-bottom">
			<view class="action-btn-group">
				<button type="primary" class=" action-btn no-border add-cart-btn" @click="backToChoose()">重新选择</button>
			</view>
			<view class="action-btn-group" style="margin-left: 90upx;">
				<button type="primary" class=" action-btn no-border add-cart-btn" @click="sureOrder()">立即预约</button>
			</view>
		</view>
	</view>
</template>

<script>
	import {
	    mapState 
	} from 'vuex'; 
	import WucTab from '@/components/wuc-tab/wuc-tab.vue';
	import { dateData, timeData, timeStamp } from './date.js';
	export default {
		data() {
			return {
				source: 0,
				serviceList: [],
				urlKey: getApp().globalData.url + "/",
				TabCur: 0,
				dateArr: [], //日期数据
				registerData:{},
				serviceData:{},
				allDate: '',
				btnOneColor: '#FFFFFF',
				btnTwoColor: '#FFFFFF',
				btnThreeColor: '#FFFFFF',
				btnFourColor: '#FFFFFF',
				btnFiveColor: '#FFFFFF',
				btnSixColor: '#FFFFFF',
				canClickOne: false,
				canClickTwo: false,
				canClickThree: false,
				canClickFour: false,
				canClickFive: false,
				canClickSix: false,
				noonStr: '',
				discount: '1'
			}
		},
		components: { WucTab },
		computed: {
			...mapState(['hasLogin','userInfo'])
		},
		created() {
			//获取日期tab数据
			this.dateArr = dateData();
		},
		onLoad(option){
			this.registerData = JSON.parse(option.register);
			this.serviceData = JSON.parse(option.service);
			this.allDate = this.dateArr[this.TabCur].allDate;
			this.loadData();
		},
		methods: {
			async loadData() {
				// let isBuycard = this.userInfo.data.isBuycard;
				// if(isBuycard == '1'){
				// 	let discount = this.userInfo.data.discount;
				// 	this.discount = discount;
				// 	this.serviceData.price = this.serviceData.price * this.discount / 10;
				// }
				
				let that = this;
				let registerId = that.registerData.id;
				let serviceId = that.serviceData.id;
				let orderDate = that.allDate;
				uni.request({ 
					url: getApp().globalData.url + '/hospital_mini/getNoon', //仅为示例，并非真实接口地址。
					method: 'POST',
					data: {
						"registerId" : registerId,
						"serviceId" : serviceId,
						"orderDate": orderDate
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						console.log(res.data.data);
						if(res.data.code == '0'){
							let OneFlag = res.data.data[10];
							if(OneFlag == '1'){
								that.canClickOne = true;
								that.btnOneColor = "#FF6347";
							}
							let TwoFlag = res.data.data[12];
							if(TwoFlag == '1'){
								that.canClickTwo = true;
								that.btnTwoColor = "#FF6347";
							}
							let ThreeFlag = res.data.data[14];
							if(ThreeFlag == '1'){
								that.canClickThree = true;
								that.btnThreeColor = "#FF6347";
							}
							let FourFlag = res.data.data[16];
							if(FourFlag == '1'){
								that.canClickFour = true;
								that.btnFourColor = "#FF6347";
							}
							let FiveFlag = res.data.data[18];
							if(FiveFlag == '1'){
								that.canClickFive = true;
								that.btnFiveColor = "#FF6347";
							}
							let SixFlag = res.data.data[20];
							if(SixFlag == '1'){
								that.canClickSix = true;
								that.btnSixColor = "#FF6347";
							}
						}else{
							uni.showToast({
								title: res.data.msg,
								icon: 'none',
								mask: true,
								duration: 10000
							});
						}
					}
				});
			},
			goOrder(timeFlag){
				if(timeFlag == 10){
					this.btnOneColor = "#9AFF9A";
					if(!this.canClickTwo){
						this.btnTwoColor = "#FFFFFF";
					}
					if(!this.canClickThree){
						this.btnThreeColor = "#FFFFFF";
					}
					if(!this.canClickFour){
						this.btnFourColor = "#FFFFFF";
					}
					if(!this.canClickFive){
						this.btnFiveColor = "#FFFFFF";
					}
					if(!this.canClickSix){
						this.btnSixColor = "#FFFFFF";
					}
					this.noonStr = "10:00";
				}else if(timeFlag == 12){
					this.btnTwoColor = "#9AFF9A";
					if(!this.canClickOne){
						this.btnOneColor = "#FFFFFF";
					}
					if(!this.canClickThree){
						this.btnThreeColor = "#FFFFFF";
					}
					if(!this.canClickFour){
						this.btnFourColor = "#FFFFFF";
					}
					if(!this.canClickFive){
						this.btnFiveColor = "#FFFFFF";
					}
					if(!this.canClickSix){
						this.btnSixColor = "#FFFFFF";
					}
					this.noonStr = "12:00";
				}else if(timeFlag == 14){
					this.btnThreeColor = "#9AFF9A";
					if(!this.canClickOne){
						this.btnOneColor = "#FFFFFF";
					}
					if(!this.canClickTwo){
						this.btnTwoColor = "#FFFFFF";
					}
					if(!this.canClickFour){
						this.btnFourColor = "#FFFFFF";
					}
					if(!this.canClickFive){
						this.btnFiveColor = "#FFFFFF";
					}
					if(!this.canClickSix){
						this.btnSixColor = "#FFFFFF";
					}
					this.noonStr = "14:00";
				}else if(timeFlag == 16){
					this.btnFourColor = "#9AFF9A";
					if(!this.canClickOne){
						this.btnOneColor = "#FFFFFF";
					}
					if(!this.canClickThree){
						this.btnThreeColor = "#FFFFFF";
					}
					if(!this.canClickTwo){
						this.btnTwoColor = "#FFFFFF";
					}
					if(!this.canClickFive){
						this.btnFiveColor = "#FFFFFF";
					}
					if(!this.canClickSix){
						this.btnSixColor = "#FFFFFF";
					}
					this.noonStr = "16:00";
				}else if(timeFlag == 18){
					this.btnFiveColor = "#9AFF9A";
					if(!this.canClickOne){
						this.btnOneColor = "#FFFFFF";
					}
					if(!this.canClickThree){
						this.btnThreeColor = "#FFFFFF";
					}
					if(!this.canClickFour){
						this.btnFourColor = "#FFFFFF";
					}
					if(!this.canClickTwo){
						this.btnTwoColor = "#FFFFFF";
					}
					if(!this.canClickSix){
						this.btnSixColor = "#FFFFFF";
					}
					this.noonStr = "18:00";
				}else if(timeFlag == 20){
					this.btnSixColor = "#9AFF9A";
					if(!this.canClickOne){
						this.btnOneColor = "#FFFFFF";
					}
					if(!this.canClickThree){
						this.btnThreeColor = "#FFFFFF";
					}
					if(!this.canClickFour){
						this.btnFourColor = "#FFFFFF";
					}
					if(!this.canClickFive){
						this.btnFiveColor = "#FFFFFF";
					}
					if(!this.canClickTwo){
						this.btnTwoColor = "#FFFFFF";
					}
					this.noonStr = "20:00";
				}
				
			},
			//详情页
			sureOrder() {
				if (this.hasLogin) {
					let userId = this.userInfo.data.userId;
					let noonStr = this.noonStr;
					let registerId = this.registerData.id;
					let serviceId = this.serviceData.id;
					let orderDate = this.allDate;
					let registerName = this.registerData.name;
					let serverName = this.serviceData.name;
					let typeInfo = this.serviceData.typeInfo;
					let price = this.serviceData.price;
					if(noonStr != ""){
						uni.showModal({
							content: '确定选择日期：' + orderDate + '  ' + noonStr + '？',
							success: (e)=>{
								if(e.confirm){
									uni.navigateTo({
										url: `/pages/order/createOrder?register=${JSON.stringify(this.registerData)}
										&service=${JSON.stringify(this.serviceData)}&noonStr=${noonStr}&orderDate=${orderDate}`
									})
								
								}
							}
						})
					}else{
						uni.showToast({
							title: "请选择具体时间",
							icon: 'none',
							mask: true,
							duration: 10000
						});
					}
				} else {
					uni.reLaunch({
						url: `/pages/public/login`
					})
				}
			},
			tabChange(index) {
			    this.TabCur = index;
				this.btnOneColor = '#FFFFFF';
				this.btnTwoColor = '#FFFFFF';
				this.btnThreeColor = '#FFFFFF';
				this.btnFourColor = '#FFFFFF';
				this.btnFiveColor = '#FFFFFF';
				this.btnSixColor = '#FFFFFF';
				this.canClickOne = false;
				this.canClickTwo = false;
				this.canClickThree = false;
				this.canClickFour = false;
				this.canClickFive = false;
				this.canClickSix = false;
				this.noonStr = '';
				this.allDate = this.dateArr[this.TabCur].allDate;
				this.loadData();
			},
			
			backToChoose(){
				uni.reLaunch({
					url: '/pages/register/register'
				})
			},
			//获取地址列表
			getServiceList(){
				let that = this;
				uni.request({
					url: getApp().globalData.url + '/hospital_mini/getServiceList',
					method: 'POST',
					data: {
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						if(res.data.code == '0'){
							that.serviceList = res.data.data;
						}
					}
				});
			}
		}
	}
</script>

<style lang='scss'>
	page{
		padding-bottom: 120upx;
		background: #f5f5f5;
	}
	
	view {
	  font-size: 20upx;
	  background-color: #f1f1f1;
	}
	
	.page-bottom{
		position:fixed;
		left: 30upx;
		bottom:15upx;
		z-index: 95;
		display: flex;
		justify-content: center;
		align-items: center;
		width: 690upx;
		height: 100upx;
		background: rgba(255,255,255,.9);
		box-shadow: 0 0 20upx 0 rgba(0,0,0,.5);
		border-radius: 16upx;
		
		.p-b-btn{
			display:flex;
			flex-direction: column;
			align-items: center;
			justify-content: center;
			font-size: $font-sm;
			color: $font-color-base;
			width: 96upx;
			height: 80upx;
			.yticon{
				font-size: 40upx;
				line-height: 48upx;
				color: $font-color-light;
			}
			&.active, &.active .yticon{
				color: $uni-color-primary;
			}
			.icon-fenxiang2{
				font-size: 42upx;
				transform: translateY(-2upx);
			}
			.icon-shoucang{
				font-size: 46upx;
			}
		}
		.action-btn-group{
			display: flex;
			height: 76upx;
			border-radius: 100px;
			overflow: hidden;
			box-shadow: 0 20upx 40upx -16upx #fa436a;
			box-shadow: 1px 2px 5px rgba(219, 63, 96, 0.4);
			background: linear-gradient(to right, #ffac30,#fa436a,#F56C6C);
			position:relative;
			// &:after{
			// 	content: '';
			// 	position:absolute;
			// 	top: 50%;
			// 	right: 50%;
			// 	transform: translateY(-50%);
			// 	height: 28upx;
			// 	width: 0;
			// 	border-right: 1px solid rgba(255,255,255,.5);
			// }
			.action-btn{
				display:flex;
				align-items: center;
				justify-content: center;
				width: 180upx;
				height: 100%;
				font-size: $font-base ;
				padding: 0;
				border-radius: 0;
				background: transparent;
			}
		}
	}
	
	.bg-white{
	    background-color: #ffffff;
	}
	
	.cu-bar {
	    display: flex;
	    position: relative;
	    align-items: center;
	    min-height: 100upx;
	    justify-content: space-between;
	}
	
	.cu-bar .action {
	    display: flex;
	    align-items: center;
	    height: 100%;
	    justify-content: center;
	    max-width: 100%;
	  background-color: #ffffff;
	}
	
	.cu-bar .action:first-child {
	    margin-left: 30upx;
	    font-size: 30upx;
	}
	
	.solid,
	.solid-bottom {
	    position: relative;
	}
	
	.solid::after,
	.solid-bottom::after{
	    content: " ";
	    width: 200%;
	    height: 200%;
	    position: absolute;
	    top: 0;
	    left: 0;
	    border-radius: inherit;
	    transform: scale(0.5);
	    transform-origin: 0 0;
	    pointer-events: none;
	    box-sizing: border-box;
	}
	
	.solid::after {
	    border: 1upx solid rgba(0, 0, 0, 0.1);
	}
	
	.solid-bottom::after {
	    border-bottom: 1upx solid rgba(0, 0, 0, 0.1);
	}
	
	.text-orange{
	  color:#f37b1d
	}
	
	.text-black{
	  color:#333333;
	}
	.bg-white{
	    background-color: #ffffff;
	}
	
	.padding {
	    padding: 30upx;
	}
	
	.margin {
	    margin: 30upx;
	}
	
	.margin-top {
	    margin-top: 30upx;
	}
	.text-center {
	    text-align: center;
	}
	
	.order-item{
		display: flex;
		flex-direction: column;
		padding-left: 30upx;
		background: #fff;
		margin-top: 16upx;
		.i-top{
			display: flex;
			align-items: center;
			height: 80upx;
			padding-right:30upx;
			font-size: $font-base;
			color: $font-color-dark;
			position: relative;
			.time{
				flex: 1;
			}
			/* .state{
				color: $base-color;
			} */
			.del-btn{
				padding: 10upx 0 10upx 36upx;
				font-size: $font-lg;
				color: $font-color-light;
				position: relative;
				&:after{
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
		.goods-box{
			height: 160upx;
			padding: 20upx 0;
			white-space: nowrap;
			.goods-item{
				width: 120upx;
				height: 120upx;
				display: inline-block;
				margin-right: 24upx;
			}
			.goods-img{
				display: block;
				width: 100%;
				height: 100%;
			}
		}
		/* 单条商品 */
		.goods-box-single{
			display: flex;
			.goods-img{
				display: block;
				width: 150upx;
				height: 150upx;
				margin-top: 24upx;
			}
			.right{
				flex: 1;
				display: flex;
				flex-direction: column;
				padding: 30upx 30upx 0 24upx;
				overflow: hidden;
				.title{
					font-size: $font-base + 8upx;
					color: #b36d41;
					line-height: 1;
				}
				.attr-box{
					font-size: $font-sm + 2upx;
					color: $font-color-light;
					padding: 10upx 12upx;
				}
				.price{
					font-size: $font-base + 2upx;
					color: $font-color-dark;
					&:before{
						content: '';
						font-size: $font-sm;
						margin: 0 2upx 0 8upx;
					}
				}
			}
		}
		
		.price-box{
			display: flex;
			justify-content: flex-end;
			align-items: baseline;
			padding: 20upx 30upx;
			font-size: $font-sm + 2upx;
			color: $font-color-light;
			.num{
				margin: 0 8upx;
				color: $font-color-dark;
			}
			.price{
				font-size: $font-lg;
				color: $font-color-dark;
				&:before{
					content: '￥';
					font-size: $font-sm;
					margin: 0 2upx 0 8upx;
				}
			}
		}
		.action-box{
			display: flex;
			justify-content: flex-end;
			align-items: center;
			height: 60upx;
			position: relative;
			padding-right: 30upx;
		}
		.action-btn{
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
			&:after{
				border-radius: 100px;
			}
			&.recom{
				background: #fff9f9;
				color: $base-color;
				&:after{
					border-color: #f7bcc8;
				}
			}
		}
	}
	
	.content{
		position: relative;
	}
	.list{
		display: flex;
		align-items: center;
		padding: 20upx 30upx;;
		background: #fff;
		position: relative;
	}
	.wrapper{
		display: flex;
		flex-direction: column;
		flex: 1;
	}
	.address-box{
		display: flex;
		align-items: center;
		.tag{
			font-size: 24upx;
			color: $base-color;
			margin-right: 10upx;
			background: #fffafb;
			border: 1px solid #ffb4c7;
			border-radius: 4upx;
			padding: 4upx 10upx;
			line-height: 1;
		}
		.address{
			font-size: 30upx;
			color: $font-color-dark;
		}
	}
	.u-box{
		font-size: 28upx;
		color: $font-color-light;
		margin-top: 16upx;
		.name{
			margin-right: 30upx;
		}
	}
	.icon-bianji{
		display: flex;
		align-items: center;
		height: 80upx;
		font-size: 40upx;
		color: $font-color-light;
		padding-left: 30upx;
	}
	
	.add-btn{
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
