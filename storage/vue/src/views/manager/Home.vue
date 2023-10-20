<template>
  <div>
    <div style="margin: 20px 0">
      <div style="font-size: 20px">
        您好，<strong style="color: orange">{{ user.name }}</strong> 欢迎您使用
        <strong style="color: #1E90FF">仓库管理系统</strong>
      </div>
    </div>

    <el-row :gutter="20">
      <el-col :span="6">
        <div class="box">
          <div style="color: #666">活跃用户</div>
          <div style="margin-top: 10px; font-size: 20px">
            {{ data.users }}
          </div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="box">
          <div style="color: #666">入库数量</div>
          <div style="margin-top: 10px; font-size: 20px">
            {{ data.in }}
          </div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="box">
          <div style="color: #666">出库数量</div>
          <div style="margin-top: 10px; font-size: 20px">
            {{ data.out }}
          </div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="box">
          <div style="color: #666">总库存量</div>
          <div style="margin-top: 10px; font-size: 20px">
            {{ data.store }}
          </div>
        </div>
      </el-col>
    </el-row >

    <div style="margin: 30px 0">
      <div style="width: 90%; height: 500px" id="main"></div>
    </div>

  </div>
</template>

<script>
import * as echarts from 'echarts';

const option = {
  title: {
    text: '仓库库存趋势图',
    left: '20px'
  },
  tooltip: {
    trigger: 'axis'
  },
  legend: {
    data: ['入库', '出库', '总库存']
  },
  grid: {
    left: '3%',
    right: '4%',
    bottom: '3%',
    containLabel: true
  },
  // toolbox: {
  //   feature: {
  //     saveAsImage: {}
  //   }
  // },
  xAxis: {
    type: 'category',
    boundaryGap: false,
    data: []
  },
  yAxis: {
    type: 'value'
  },
  series: [
    {
      name: '入库',
      type: 'line',
      smooth: true,
      data: []
    },
    {
      name: '出库',
      type: 'line',
      smooth: true,
      data: []
    },
    {
      name: '总库存',
      type: 'line',
      smooth: true,
      data: []
    },
  ]
};

export default {
  name: 'Home',
  data() {
    return {
      data: {},
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  mounted() {
    this.request.get('/data').then(res => this.data = res?.data)

    const chartDom = document.getElementById('main')
    const myChart = echarts.init(chartDom)
    this.request.get('/line').then(res => {
      option.series[0].data = res.data?.in
      option.series[1].data = res.data?.out
      option.series[2].data = res.data?.store
      option.xAxis.data = res.data?.dates
      myChart.setOption(option)
    })


  },
  methods: {}
}
</script>

<style scoped>
.box {
  width: 100%;
  box-shadow: 0 0 10px -2px rgba(0, 0, 0, .2);
  border-radius: 5px;
  padding: 20px;
  text-align: center;
}
</style>
