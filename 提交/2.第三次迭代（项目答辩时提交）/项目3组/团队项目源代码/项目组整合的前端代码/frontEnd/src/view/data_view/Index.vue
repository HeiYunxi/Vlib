<template>
  <div id="main">
    <el-card>
      <el-row :gutter="20" style="height: 80px;">
        <el-col :span="4">
          <div class="head_div" style="background-color: #337ecc">
            <h3 style="color: whitesmoke">{{ dataNum.teacher }}</h3>
            <span style="font-size: 20px;color: white">教师</span>
          </div>
        </el-col>
        <el-col :span="4">
          <div class="head_div" style="background-color: #EC1352DC">
            <h3 style="color:whitesmoke ">{{ dataNum.student }}</h3>
            <span style="font-size: 20px;color: white">学生</span>
          </div>
        </el-col>
        <el-col :span="4">
          <div class="head_div" style="background-color:#67c23a">
            <h3 style="color: whitesmoke">{{ dataNum.admin }}</h3>
            <span style="font-size: 20px;color: white">管理员</span>
          </div>
        </el-col>
        <el-col :span="4">
          <div class="head_div" style="background-color:#901ddc;">
            <h3 style="color: whitesmoke">{{ dataNum.equipmentSupplier }}</h3>
            <span style="font-size: 20px;color: white">供应商</span>
          </div>
        </el-col>
        <el-col :span="4">
          <div class="head_div" style="background-color:#67c23a">
            <h3 style="color: whitesmoke">{{ dataNum.equipmentCount }}</h3>
            <span style="font-size: 20px;color: white">设备</span>
          </div>
        </el-col>
        <el-col :span="4">
          <div class="head_div" style="background-color:#67c23a">
            <h3 style="color: whitesmoke">{{ dataNum.equipmentLabCount }}</h3>
            <span style="font-size: 20px;color: white">实验室</span>
          </div>
        </el-col>
      </el-row>
    </el-card>
    <el-row style="margin-top: 20px" :gutter="20">
      <el-col :span="8">
        <el-card>
          <template #header>
            <span>用户分布</span>
          </template>
          <div id="barChat" style="height: 160px;width: 100%"></div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card>
          <template #header>
            <span>设备情况占比</span>
          </template>
          <div id="pieChat" style="height: 160px"></div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card>
          <template #header>
            <span>实验室设备容量</span>
          </template>
          <div id="columnarChat" style="height: 160px"></div>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="20" style="height: 300px;">
      <el-col :span="24">
        <el-card style="height: 100%">
          <div>
            <span style="font-weight: bolder;font-size: 20px">实验室申请审批人数</span>
          </div>
          <hr>
          <div id="lineChart" style="width: 100%;height: 300px"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import {ref, onMounted, onBeforeMount, reactive} from "vue"
import {ElMessage, ElMessageBox} from 'element-plus';
import dataHttp from "../../api/data_view.js"
import * as echarts from "echarts";



export default {
  name: "dataView",
  setup() {
    let dataNum = ref({
      "equipmentCount": 0,
      "teacher": 0,
      "equipmentSupplier": 0,
      "student": 0,
      "admin": 0,
      "equipmentLabCount": 0
    });
    onBeforeMount(async () => {
      let res1 = await dataHttp.getData1();
      dataNum.value = res1.data;
    })
    const Barchart =async () => {
      let myChart = echarts.init(document.getElementById('barChat'));
      let res = await dataHttp.getBarData();
      let BarData = res.data.data
      var yData = BarData.map(item=>item.roleName)
      var xData =  BarData.map(item=>item.countNum)
      let option = {
        grid: {
          top: 10,
          left: 75,
          bottom: 0,

        },
        xAxis: {
          type: 'value',
          splitLine: {
            lineStyle: {
              color: 'rgba(77, 128, 254, 0.2)',
              type: 'line',
            },
          },
          axisTick: {
            show: true,
          },
          axisLabel: {
            //  改变x轴字体颜色和大小
            show: true,
            textStyle: {
              fontSize: 16,
            },
          },
        },
        yAxis: [
          {
            type: 'category',
            inverse: true,
            axisLabel: {
              show: true,
              formatter: (params) => {
                console.log(params);
                let str = params.split("-");
                return "{a| " + str[0] + "}";
              },
              rich: {
                a: {
                  align: "right",
                  padding: 4,
                  color: "#666666"
                },
                b: {
                  align: "right",
                  padding: 4,
                  color: "#B5B5B5"
                },
              },
            },
            splitLine: {
              show: false,
            },
            axisTick: {
              show: false,
            },
            axisLine: {
              show: false,
            },
            data: yData,
          },
        ],
        series: [
          {
            name: '',
            type: 'bar',
            zlevel: 1,
            label: {
              show: true,
              position: 'right', // 位置
              color: '#49D6F6',
              fontSize: 14,
              distance: 10, // 距离
              formatter: '{c}个' // 这里是数据展示的时候显示的数据
            }, // 柱子上方的数值
            itemStyle: {
              normal: {
                barBorderRadius: 0,
                color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [
                  {
                    offset: 0,
                    color: '#49D6F6',
                  },
                  {
                    offset: 1,
                    color: '#49D6F6',
                  },
                ]),
              },
            },
            barWidth: 20,
            data: xData,
          },
        ],
      };

      myChart.setOption(option);
    }
    const Piechart = async () => {
      let myChart = echarts.init(document.getElementById('pieChat'));
      let res = await dataHttp.getPieData();
      console.log(res.data.data)
      const annularList= res.data.data
      // 真实后端数据
      // const annularList = [
      //   {value: 33, name: "一班"},
      //   {value: 44, name: "二班"},
      //   {value: 57, name: "三班"},
      //   {value: 62, name: "四班"},
      //   {value: 27, name: "五班"},
      // ];


      // 颜色
      const colorList = {
        可用: ["#49D6F6", "#49D6F6"],
        正在维护: ["#7AE89D", "#7AE89D"]
      };

      // 组装数据
      const newAnnularList = annularList.map((item) => {
        return {
          ...item,
          itemStyle: {
            color: new echarts.graphic.RadialGradient(0, 0, 1, [
              {
                offset: 0,
                color: colorList[item.name][0],
              },
              {
                offset: 1,
                color: colorList[item.name][1],
              },
            ]),
          },
        };
      });

      // 配置项
      const option = {
        legend: {
          top: "bottom"
        },
        tooltip: {
          show: true,
          formatter: "{b}: {c} 个",
        },
        series: [
          {
            name: "信息上报来源统计事故",
            type: "pie",
            radius: ["40%", "60%"],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 4,
              borderColor: "#fff",
              borderWidth: 2,
            },
            label: {
              show: true,
              position: "outside",
              formatter: (params) => {
                return "{a| " + params.name + "}\n{b| " + params.value + "个}";
              },
              rich: {
                a: {
                  align: "left",
                  padding: 4,
                },
                b: {
                  align: "left",
                  padding: 4,
                  fontSize: 14,
                  fontWeight: 600,
                },
              },
            },
            labelLine: {
              show: true,
              length: 20,
              length2: 30,
            },
            data: newAnnularList,
          },
        ],
      };
      myChart.setOption(option);
    }
    const columnarChart =async () => {
      let myChart = echarts.init(document.getElementById('columnarChat'));
      let res = await dataHttp.getColumnarData();
      let ColumnarData = res.data.data
      let option = {
        tooltip: {
          trigger: 'axis',
          formatter: '{a1}<br/>{b1}：{c1}人'
        },
        grid: {
          top: 20,
          bottom: 20
        },
        xAxis: {
          data: ColumnarData.map(item=>item.name),
          axisLabel: {

            showMinLabel: true,
            interval: 0 // 0 表示显示所有标签
          }
        },
        color: ['#ccc', 'red'],
        yAxis: {
          axisLabel: {
            formatter: '{value}'
          }

        },
        series: [
          // 流光效果
          {
            name: '',
            type: 'bar',
            barGap: '-100%',
            // 这里不要超出实际数据
            data: ColumnarData.map(item=>item.value),
            itemStyle: {
              // 颜色渐变
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: '#b1cae640'
                },
                {
                  offset: 1,
                  color: '#2f88e778'
                }
              ])
            }
          },
          // 背景，要显示的数据
          {
            name: '人数',
            type: 'bar',
            // 充当背景
            z: '-1',
            // 真实数据
            data: ColumnarData.map(item=>item.value),
            itemStyle: {
              color: '#14eca4'
            }
          }
        ]
      };

      /*
        先将流光数据变为 0，再将真实数据赋值给流光数据
        每次都执行 setOption 更新数据，实现动画效果
      */
      function run1() {
        let length = option.series[0].data.length;
        let data = new Array(length).fill[0];
        let newD = {series: [{data}]};
        myChart.setOption(newD);
      }

      function run2() {
        let data = [5, 20, 36, 10, 10, 20];
        let newD = {series: [{data}]};
        myChart.setOption(newD);
      }

      setInterval(function () {
        run1();
      }, 1000);
      setInterval(function () {
        run2();
      }, 1000);
      myChart.setOption(option)
    }
    const lineChart = async () => {
      let myChart = echarts.init(document.getElementById('lineChart'));
      let res5 = await dataHttp.getLineData();
      let LineData = res5.data.data.sort();
      let option = {
        tooltip: {
          trigger: 'axis',
        },
        grid: {
          top: '4%',
          left: '2%',
          right: '4%',
          bottom: '0',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          data: LineData.map(item=>item.reviewDatetime),
          axisLabel: {
            color: '#CFCFCF',
            interval: 0,
            fontSize: 14
          },
          axisTick: {
            show: false
          }
        },
        yAxis: {
          type: 'value',
          axisLabel: {
            show: true,
            color: '#CFCFCF',
            fontSize: 14
          },

          splitLine: {
            show: true,
            lineStyle: {
              width: 0.8,
              color: 'rgba(127, 214, 255, .4)',
              type: 'dashed'
            }
          }
        },
        series: [
          {
            type: 'line',
            showSymbol: false,
            smooth: true,
            // symbolSize: 8,
            lineStyle: {
              color: '#00FFF6'
            },
            itemStyle: {
              color: '#00FFF6',
              borderColor: '#00FFF6',
              borderWidth: 2
            },
            areaStyle: {
              color: {
                type: 'linear',
                x: 0,
                y: 0,
                x2: 0,
                y2: 1,
                colorStops: [
                  {
                    offset: 0.25,
                    color: '#00FFF686'
                  },
                  {
                    offset: 1,
                    color: '#00FFF600'
                  }
                ]
              }
            },
            data: LineData.map(item=>item.count)
          }
        ]
      }
      myChart.setOption(option)
    }
    onMounted(() => {
      Barchart();
      Piechart();
      columnarChart();
      lineChart();
    })
    return {
      dataNum
    };
  }

}
</script>
<style scoped>
.head_div {
  height: 100%;
  background-color: #337ecc;
  text-align: center;
  position: relative;
  top: -25%;
  border-radius: 10px;
}

.head_div h3 {
  position: relative;
  top: 13%;
}

.el-row {
  margin-bottom: 20px;
}

.el-row:last-child {
  margin-bottom: 0;
}

.el-col {
  border-radius: 4px;
  padding-left: 20px;
}


.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

template {
  overflow-y: scroll;
}
</style>
