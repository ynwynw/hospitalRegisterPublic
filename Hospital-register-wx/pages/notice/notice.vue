<template>
	<view>
		<view class="notice-item"  v-for="(proclamation, index) in proclamationList" :key="index">
			<text class="time">{{proclamation.createTime}}</text>
			<view class="content">
				<text class="title">{{proclamation.title}}</text>
				<text class="type-flag">
					<uni-tag :text="proclamation.proType == '0'? '校园讯息' : '社会讯息'" size="small" type="error" :circle="true" :inverted="true"></uni-tag>
				</text>
				<text class="introduce">
					{{proclamation.content}}
				</text>
				<view class="bot b-t">
				
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import uniTag from "@/components/uni-tag/uni-tag.vue"
	export default {
		components: {uniTag},
		data() {
			return {
				proclamationList: []
			}
		},
		onLoad(){
			this.loadData();
		},
		methods: {
			//请求数据
			loadData(){
				let that = this;
				uni.request({
					url: getApp().globalData.url + '/transaction/hospital_mini/proclamationList',
					method: 'POST',
					data: {
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						if(res.data.code == '0'){
							that.proclamationList = res.data.data;
						}
					}
				});
			}
		}
	}
</script>

<style lang='scss'>
	page {
		background-color: #f7f7f7;
		padding-bottom: 30upx;
	}

	.notice-item {
		display: flex;
		flex-direction: column;
		align-items: center;
	}

	.time {
		display: flex;
		align-items: center;
		justify-content: center;
		height: 80upx;
		padding-top: 10upx;
		font-size: 26upx;
		color: #7d7d7d;
	}

	.content {
		width: 710upx;
		padding: 0 24upx;
		background-color: #fff;
		border-radius: 4upx;
	}

	.title {
		display: flex;
		align-items: center;
		height: 90upx;
		font-size: 32upx;
		color: #303133;
	}
	
	.type-flag{
		padding: 2upx 0;
	}

	.introduce {
		display: inline-block;
		padding: 16upx 0;
		font-size: 28upx;
		color: #606266;
		line-height: 38upx;
	}

	.bot {
		height: 16upx;
	}
</style>
