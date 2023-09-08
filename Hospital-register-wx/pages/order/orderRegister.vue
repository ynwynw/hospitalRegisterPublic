<template>
	<view>
		<!-- 医生信息 -->
		<view class="order-item">
			<view class="goods-box-single">
				<view class="goods-img">
					<image :src="urlKey+serviceData.logo" mode="aspectFill" style="width: 100%;height: 100%;"></image>
				</view>

				<view class="right">
					<view class="one">
						<text class="name">{{serviceData.name}}</text>
						<text class="work">{{serviceData.typeInfo}}</text>
					</view>
					<view class="two">
						<text class="register_name">就诊科室:</text>
						<text class="register">{{serviceData.registerName}}</text>
					</view>
				</view>
			</view>
			<!-- <view class="action-box b-t">
				<view class="action-btn recom" @click="goChooseDatePage(item)">预约</view>
			</view> -->
		</view>
		<view class="destinatoin">
			<view class="doctor_desc">
				<view class="desc">其他事项</view>
				<view class="other">医师服务费&nbsp;&nbsp;&nbsp;{{serviceData.price}}元</view>
				<view class="introduce">医生简介：{{serviceData.introduce}}</view>
			</view>
			<view class="doctor_detail">
				<view class="detail_center">
					<!-- <view class="content_opa" :class="showAll ? 'showAll' : ''">
						{{introduce.length>limit ? ( showAll ? introduce : introduce.slice(0, limit) + '...') : introduce }}
					</view>
					<view class="operation" @tap="toggleShow">
						{{introduce.length>limit ? ( showAll ? '收起' : '展开') : '' }}
					</view> -->
				</view>
			</view>
		</view>
		<!-- 就诊人信息 -->
		<view class="patient-info">
			<view class="title">
				信息填写
			</view>
			<view class="patient">
				<view class="input-wrapper">
					<text class="txt1">就诊人</text>
					<input class="input" type="text" :value="contactName" disabled placeholder="填写就诊人姓名" />
					<view class="choose">
						<picker mode="selector" :range=" patientList " range-key="contactName"
							@change="changeDepartment">
							<view class="txt">
								{{ message }}
							</view>
						</picker>
					</view>
				</view>
				<view class="input-wrapper">
					<text class="txt1">手机号</text>
					<input class="input" type="text" :value="phone" disabled placeholder="填写就诊人手机号" />
				</view>
				<view class="input-wrapper">
					<text class="txt1">就诊时间</text>
					<!-- <input class="input" :value="date" type="text" /> -->

					<picker class="pi" mode="selector" :range=" dateList " range-key="d" @change="changeSchedule">
						<text class="schedu">
							{{date}}
						</text>
						<text class="txt">
							选择就诊时间
						</text>
					</picker>

				</view>
			</view>

		</view>
		<!-- 疾病描述 -->
		<view class="disease">
			<view class="disease_description">疾病描述</view>
			<textarea class="description" placeholder="请描述就诊人的疾病/病症..." v-model="description"></textarea>
			<view class="on_sub">
				<button class="submit" @click="onSubmit()">确认提交</button>
			</view>
		</view>
		<!-- 确认提交按钮 -->

	</view>
</template>

<script>
	import {
		mapState
	} from 'vuex';
	export default {
		data() {
			return {
				message: '选择就诊人...',
				departments: ['内科', '外科', '儿科'],
				patientList: [],
				date: '',
				schedules: [],
				scheduleList: [],
				dateList: [{
					d: ''
				}],
				contactName: '',
				phone: '',
				doctor: {
					avatar: '', // 医生头像
					name: '', // 医生名字
					department: '' // 就诊科室
				},
				patients: [], // 就诊人列表
				patientIndex: 0, // 当前选择的就诊人下标
				showNewPatient: false, // 是否显示新建就诊人输入框
				newPatientName: '', // 新建就诊人姓名
				phone: '', // 手机号
				times: [], // 就诊时间列表
				timeIndex: 0, // 当前选择的就诊时间下标
				description: '', // 疾病描述
				serviceData: {},
				urlKey: getApp().globalData.url + "/",
				noon: false,
				noon1: false,
				w: [{
						sw: ''

					}

				]
			}
		},
		computed: {
			...mapState(['hasLogin', 'userInfo', 'citys'])
		},
		onLoad: function(option) {
			console.log("option", option)
			this.serviceData = JSON.parse(option.serviceData);
			this.schedules = JSON.parse(option.schedules);
			this.scheduleList = JSON.parse(option.scheduleList);
			console.log("this.serviceData111", this.serviceData)
			this.getRegister()
			// console.log("this.schedules",this.schedules)
			// console.log("this.scheduleList",this.scheduleList)
			this.dateList.pop()
			this.w.pop()
			for (var i = 0; i < this.scheduleList.length; i++) {
				console.log("this.scheduleList", this.scheduleList[i].day)
				let year = this.scheduleList[i].year
				let month = this.scheduleList[i].month
				let day = this.scheduleList[i].day
				let sw = year + '-' + month + '-' + day + " " + '上午'
				let xw = year + '-' + month + '-' + day + " " + '下午'



				this.dateList.push({
					d: sw
				})
				this.dateList.push({
					d: xw
				})
			}
			// console.log("thsi111", this.dateList)
			let a = this.dateList.d + this.dateList.d
			// console.log("thsi", a)
			let year = this.schedules.year
			let month = this.schedules.month
			let day = this.schedules.day
			let w = JSON.parse(option.w)
			// console.log("111")
			// console.log("w", w)
			// console.log("222")
			this.date = year + '-' + month + '-' + day + ' ' + w

			// console.log(year)
			// console.log(month)
			// console.log(day)
			// console.log(this.schedules)
			// console.log("this.date", this.date)
			this.getPatient()



		},
		methods: {

			// onsubmit(){},

			changeSchedule(e) {
				// console.log("e", e)
				// console.log("this.changeSchedule", this.schedules)
				// console.log("this.patientList.name[e.detail.value]", this.dateList[e.detail.value])
				// console.log("this.patientList.name[e.detail.value]", e.detail.value)
				// let year = this.dateList[e.detail.value].year
				// let month = this.dateList[e.detail.value].month
				// let day = this.dateList[e.detail.value].day
				// this.date = year + '-' + month + '-' + day

				// console.log("date", this.date)

				// // console.log("this.item",this.item)
				this.date = this.dateList[e.detail.value].d
				console.log(this.noon)
				this.noon = false
				this.noon1 = false
				this.getServiceOrderInfoList1()
				this.getServiceOrderInfoByServiceId()
			},
			changeDepartment(e) {
				// console.log("e", e)
				// console.log("this.patientList", this.patientList)
				// console.log("this.patientList.name[e.detail.value]", this.patientList[e.detail.value])
				// console.log("this.patientList.name[e.detail.value]", e.detail.value)

				this.contactName = this.patientList[e.detail.value].contactName
				this.phone = this.patientList[e.detail.value].contactPhone
				this.item = this.patientList[e.detail.value]
				// console.log("contactName", this.contactName)
				this.noon = false
				this.getServiceOrderInfoList1()
				this.noon1 = false
				this.getServiceOrderInfoByServiceId()
				// console.log("this.item",this.item)
			},

			getPatient() {

				let that = this;
				let userId = that.userInfo.data.userId;
				uni.request({
					url: getApp().globalData.url + '/hospital_mini/getAddressList',
					method: 'POST',
					data: {
						"userId": userId
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						if (res.data.code == '0') {
							that.patientList = res.data.data;
							console.log("that.patientList", that.patientList)
						}
					}
				});

			},
			getRegister() {
				let registerId = this.serviceData.registerId;
				uni.request({
					url: getApp().globalData.url + '/hospital_mini/getRegisterByRegisterId',
					method: 'POST',
					data: {
						"id": registerId
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						if (res.data.code == '0') {
							console.log("res.data", res)
							this.serviceData.registerName = res.data.data.name;
							console.log("that.patientList", res.data.data)
						}
					}
				});
			},
			onPatientChange(e) {
				this.patientIndex = e.target.value
				this.showNewPatient = false
			},
			onAddPatient() {
				this.showNewPatient = !this.showNewPatient
				if (!this.showNewPatient) {
					this.newPatientName = ''
				}
			},
			onTimeChange(e) {
				this.timeIndex = e.target.value
			},

			getServiceOrderInfoList1() {

				let contactName = this.contactName;
				let userId = this.userInfo.data.userId;
				uni.request({
					url: getApp().globalData.url + '/hospital_mini/getServiceOrderInfoList1',
					method: 'POST',
					data: {
						"contactName": contactName,
						"createBy": userId
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						if (res.data.code == '0') {
							var arr = []
							arr = res.data.data;
							console.log("res.data111", arr)
							for (var i = 0; i < arr.length; i++) {
								// console.log("res.data.data.orderDate", this.date)
								// console.log("res.data.data.orderDate", arr[i].orderDate)
								// console.log("res.data.data.orderDate", arr[i].orderDate + " " + arr[i]
								// 	.orderTime)
								// console.log("this.noon", arr[i].orderDate + " " + arr[i].orderTime == this
								// 	.date)
								if (arr[i].orderDate + " " + arr[i].orderTime == this.date) {
									console.log("this.noon")
									this.noon = true;
									break;
								}
							}

						}
					}
				});
			},
			getServiceOrderInfoByServiceId() {

				let serviceId = this.serviceData.id;
				uni.request({
					url: getApp().globalData.url + '/hospital_mini/getServiceOrderInfoList',
					method: 'POST',
					data: {
						"serviceId": serviceId,
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						if (res.data.code == '0') {
							var arr = []
							arr = res.data.data;
							console.log("res.data111", arr)
							for (var i = 0; i < arr.length; i++) {
								console.log("arr[i].orderDate +' ' + arr[i].orderTime == this.date",arr[i].orderDate +
									" " + arr[i].orderTime)
									console.log("this.date",this.date)
									console.log("arr[i].serviceStatus == 1",arr[i].serviceStatus == 1)
									console.log("arr[i].payStatus == 0",arr[i].payStatus == 0)
								if (arr[i].serviceStatus == 0 && arr[i].payStatus == 0 && arr[i].orderDate +
									" " + arr[i].orderTime == this.date) {
									// console.log("this.noon1")
									console.log("arr",arr[i])
									this.noon1 = true;
									break;
								}
								console.log("this.noon1",this.noon1)
								
							}

						}
					}
				});
			},


			 onSubmit() {
				console.log("this.noon", this.noon)
				let contactName = this.contactName;
				let contactPhone = this.phone;
				console.log("contactName",contactName)
				if (this.noon == true) {
					console.log("121415")
					uni.showToast({
						title: '该时间段您已经预约了医生，请选择其他时间段！',
						icon: 'none'
					})
				} else if (this.noon1 == true) {
					uni.showToast({
						title: '该时间段医生已有约，请选择其他时间！',
						icon: 'none'
					})

				} else if (contactName == '' && contactPhone == '' ) {
					uni.showToast({
						title: '请选择就诊人，如果没有就诊人，先去个人中心添加就诊人！',
						icon: 'none'
					})
				} else {
					let createBy = this.userInfo.data.userId;
					let doctorId = this.serviceData.userId;

					let registerId = this.serviceData.registerId;
					let serviceId = this.serviceData.id;
					let description = this.description;
					var arr = this.date.split(" ")
					var orderDate = arr[0];
					var noonStr = arr[1];
					let areaId = this.citys.areaId
					let hospitalId = this.serviceData.hospitalId
					console.log("arr", arr)
					console.log("arr", orderDate)
					console.log("arr", noonStr)
					let registerName = this.serviceData.registerName;
					let serverName = this.serviceData.name;
					let typeInfo = this.serviceData.typeInfo;
					let price = this.serviceData.price;
					let contactName = this.contactName;
					let contactPhone = this.phone;
					//提示输入
					uni.request({
						url: getApp().globalData.url + '/hospital_mini/createServiceOrder',
						method: 'POST',
						data: {
							"createBy": createBy,
							"registerId": registerId,
							"serviceId": serviceId,
							"registerName": registerName,
							"serverName": serverName,
							"typeInfo": typeInfo,
							"price": price,
							"orderDate": orderDate,
							"orderTime": noonStr,
							"contactName": contactName,
							"contactPhone": contactPhone,
							"userId": doctorId,
							"illTroditional": description,
							"areaId": areaId,
							"hospitalId": hospitalId
						},
						header: {
							'content-type': 'application/x-www-form-urlencoded'
						},
						success: (res) => {
							console.log("res.data", res.data)
							uni.navigateTo({
								url: '/pages/order/successOrder'
							})
							console.log("222")
						}
					});
				}

			},




		}

	}
</script>

<style lang="scss">
	/* 医生信息样式 */
	page {
		padding-bottom: 120upx;
		background: #f5f5f5;
	}

	.pi {
		width: 70%;
		height: 70rpx;
		display: inline-block;
		line-height: 70rpx;
		padding-left: 10rpx;
		color: #828282;
		font-size: 30rpx;
		// background-color: #007aff;

		.schedu {
			width: 50%;
			// height: 70rpx;
			// background-color: #ffaa00;
		}

		.txt {
			width: 50%;
			// width: 10%;
			// background-color: #ccc;
			margin-left: 100rpx;
		}
	}

	.destinatoin {


		width: 100%;
		/* height: 300rpx; */
		margin-top: 20rpx;
		// background-color: red;
		background-color: #fff;

		.doctor_desc {
			width: 100%;
			// height: 70rpx;
			display: block;

			// background-color: #ff6600;
			.desc {
				// height: 70rpx;
				line-height: 70rpx;
				margin-left: 20rpx;
				// font-weight: bolder;
				font-size: 30rpx;
				// background-color: #007aff;
				margin-right: 20rpx;
			}

			.other {
				// height: 70rpx;
				line-height: 70rpx;
				margin-left: 20rpx;
				margin-right: 20rpx;
				// font-weight: bolder;
				font-size: 30rpx;
				// background-color: #333;
			}

			.introduce {
				// line-height: 70rpx;
				margin-left: 20rpx;
				// font-weight: bolder;
				font-size: 25rpx;
				margin-right: 20rpx;
				padding-bottom: 10rpx;
			}
		}

		.doctor_detail {
			color: #494a4feb;
			font-family: $border-color-dark;
			background-color: #fff;

			border-top: 1rpx solid #EBEEF5;
			/* position: relative; */
			padding: 10rpx;
			width: 100%;
			/* height: 400rpx; */


			.detail_center {
				width: calc(100% - 40rpx);
				/* height: calc(100% - 60rpx); */
				margin: 20rpx;

				.content_opa {

					font-size: 30rpx;
					font-family: $border-color-dark;
					white-space: pre-wrap;
					overflow: hidden;
					text-overflow: ellipsis;
					/* height: inherit; */

				}

				.content_opa.showAll {
					/* max-height: 300rpx; */
				}

				.operation {
					font-size: 30rpx;
					text-align: right;
					color: #007aff;
					padding: 10rpx;
				}

			}


		}

	}

	.order-item {

		display: flex;
		flex-direction: column;
		padding-left: 30upx;
		background: #fff;

		margin-top: 10rpx;


		/* .i-top {} */

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

				// background-color: red;
				.one {
					height: 60rpx;
					width: 100%;

					// background-color: #007aff;
					.name {
						width: 90rpx;
						height: 60rpx;
						line-height: 60rpx;
						// background-color: #ff6600;
						font-size: 35rpx;
						font-weight: bold;
					}

					.work {
						width: 50%;
						height: 60rpx;
						line-height: 60rpx;
						// background-color: #666;
						margin-left: 30rpx;
						font-size: 30rpx;
					}


				}

				.two {
					height: 60rpx;
					width: 100%;
					font-size: 30rpx;

					// background-color: #007aff;
					.register_name {
						width: 90rpx;
						height: 60rpx;
						line-height: 60rpx;
						// background-color: #ff6600;

					}

					.register {
						width: 50%;
						height: 60rpx;
						line-height: 60rpx;
						// background-color: #666;
						margin-left: 30rpx;
					}


				}





			}
		}




	}

	.doctor-info {
		display: flex;
		align-items: center;
		padding: 20rpx;
		background-color: #fff;
		box-shadow: 0 2rpx 4rpx rgba(0, 0, 0, 0.1);
	}

	.doctor-info .avatar {
		width: 100rpx;
		height: 100rpx;
		border-radius: 50%;
		margin-right: 20rpx;
	}

	.doctor-info .info {
		display: flex;
		flex-direction: column;
	}

	.doctor-info .name {
		font-size: 32rpx;
		font-weight: bold;
	}

	.doctor-info .department {
		font-size: 28rpx;
		color: #666;
	}

	/* 就诊人信息样式 */
	.patient-info {
		margin-top: 20rpx;
		background-color: #fff;
		box-shadow: 0 2rpx 4rpx rgba(0, 0, 0, 0.1);
		padding: 20rpx;
		// background-color: #007aff;

		.title {
			line-height: 40rpx;
			margin-left: 20rpx;
			// font-weight: bolder;
			font-size: 30rpx;
			// background-color: #007aff;
			margin-right: 20rpx;
		}
	}

	.patient-info .patient {
		// display: flex;
		align-items: center;
		margin-bottom: 20rpx;
		// background-color: red;
		// padding: 10rpx;


		.input-wrapper {
			display: flex;
			height: 70rpx;
			// background-color: #666;
			margin-top: 10rpx;
			border: 1rpx solid #fff;
			border-radius: 10rpx;
			// line-height: 70rpx;
			// padding: 10rpx;
			font-size: 30rpx;
			background-color: #f9f9f9;

			.txt1 {
				width: 25%;
				height: 30rpx;
				line-height: 30rpx;
				// margin: 10rpx;
				margin-top: 20rpx;
				// margin-left: 20rpx;
				// // line-height: 10rpx;
				// padding-left: 20rpx;
				border-right: 1px solid #ccc;
				// border-right: #000;
				// border: 1px solid #000;
				text-align: center;
			}

			input {
				width: 45%;
				height: 70rpx;
				line-height: 70rpx;
				padding-left: 10rpx;
				// border: 1px solid #666;

			}

			.choose {
				width: 30%;
				// padding-left: 10rpx;
				font-size: 25rpx;
				// border: 1rpx solid red;
				height: 70rpx;
				// line-height: 70rpx;
				// text-align: center;

				// padding: 10rpx;
				text-align: center;

				picker {

					// height: 70rpx;
					// line-height: 70rpx;
					.txt {
						// width: 100%;
						// height: ;
						// padding: 10rpx;
						// padding-left: 10rpx;
						// padding-right: 10rpx;
						margin-top: 15rpx;
						border: 1rpx solid #fff;
						background-color: skyblue;
						border-radius: 30rpx;
					}
				}

			}


		}


	}


	.patient-info .patient .new-patient {
		width: 20%;
		height: 40rpx;
		margin-left: 20rpx;
		font-size: 30rpx;
		color: #999;
		border: 1px solid green;
		// margin-bottom: 10rpx;
		// padding: 20rpx;
		outline: none;

		border-radius: 10rpx;
		margin-top: 10rpx;
		padding-left: 10rpx;
		border-right: none;
	}

	.patient-info .phone {
		font-size: 28rpx;
		color: #333;
		padding: 10rpx;
		border: none;
		outline: none;
		border-bottom: 1rpx solid #eee;
		margin-bottom: 20rpx;
	}

	.patient-info .picker {
		font-size: 30rpx;
		color: #333;
	}

	.patient-info .picker-arrow {
		width: 0;
		height: 0;
		border-style: solid;
		border-width: 10rpx 5rpx 0 5rpx;
		border-color: #666 transparent transparent transparent;
	}

	/* 疾病描述样式 */
	.disease {
		margin-top: 20rpx;
		background-color: #fff;
		box-shadow: 0 2rpx 4rpx rgba(0, 0, 0, 0.1);
		padding: 20rpx;

		.disease_description {
			line-height: 40rpx;
			margin-left: 20rpx;
			// font-weight: bolder;
			font-size: 30rpx;
			// background-color: #007aff;
			margin-right: 20rpx;
		}
	}

	.disease .description {
		width: 100%;
		height: 200rpx;
		font-size: 28rpx;
		color: #333;
		border: 1rpx solid #fff;
		padding: 20rpx;
		background-color: #f9f9f9;
		outline: none;
		margin-top: 20rpx;
	}

	/* 确认提交按钮样式 */
	.on_sub {
		width: 100%;
		// background-color: #007aff;
		text-align: center;

		.submit {
			width: 50%;
			background-color: #07b6b6;
			color: #fff;
			font-size: 30rpx;
			text-align: center;
			// padding: 20rpx;
			margin-top: 20rpx;
			border-radius: 30rpx;
		}
	}
</style>
