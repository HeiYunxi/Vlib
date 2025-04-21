<template>
  <el-card>
  <!-- 查询条件 -->
  <el-form :inline="true" :model="queryParams">
    <el-form-item label="申请人">
      <el-input v-model="queryParams.userName" placeholder="请输入申请人" clearable/>
    </el-form-item>
    <el-form-item label="申请状态">
      <el-select v-model="queryParams.applyStatus" placeholder="请选择申请状态" clearable>
        <el-option v-for="item in applyStatusOpt" :label="item.label" :value="item.value" :key="item.value"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item>
      <el-button :icon="Search" type="primary" @click="handleQuery">查询</el-button>
      <el-button :icon="Refresh" @click="handleReset">重置</el-button>
    </el-form-item>
  </el-form>
  <!-- 查询条件 -->

  <!-- 工具栏 -->
  <el-row class="mb-4" style="margin-bottom: 20px">
    <!--    <el-button :icon="Plus" type="primary" @click="dialogFormVisible = true;dialogTitle='新增'">新增</el-button>-->
    <el-button :icon="Download" type="success" @click="handleExport">导出</el-button>
  </el-row>
  <!-- 工具栏 -->

  <!-- 列表 -->
  <el-table :data="tableData" border style="width: 90%">
    <el-table-column prop="userName" label="申请人" width="70"/>
    <el-table-column prop="applyNum" label="申请编号" width="180"/>
    <el-table-column prop="labName" label="申请实验室名称" width="130"/>
    <el-table-column prop="applyDay" label="申请天数" width="85"/>
    <el-table-column prop="applyReason" label="申请原因" width="100"/>
    <el-table-column :formatter="formatter" prop="applyStatus" label="申请状态" width="100"/>
    <el-table-column prop="applyDatetime" label="申请时间" width="180"/>
    <el-table-column prop="reviewComments" label="审批意见" width="85"/>
    <el-table-column prop="reviewDatetime" label="审批时间" width="180"/>
    <el-table-column label="操作" width="120">
      <template #default="scope">

        <el-button :icon="Edit" type="success" @click="handleEdit(scope.$index, scope.row)">审批</el-button>

      </template>
    </el-table-column>
  </el-table>
  <!-- 列表 -->

    <!--不显示，用户导出-->
    <div v-show="false">
      <el-table id="table-export" :data="tableData" border style="width: 100%;margin-top:20px">
        <el-table-column prop="applyId" label="申请id" width="70"/>
        <el-table-column prop="userName" label="申请人" width="70"/>
        <el-table-column prop="applyNum" label="申请编号" width="200"/>
        <el-table-column prop="labName" label="申请实验室名称" width="200"/>
        <el-table-column prop="applyDay" label="申请天数" width="100"/>
        <el-table-column prop="applyReason" label="申请原因" width="180"/>
        <el-table-column :formatter="formatter" prop="applyStatus" label="申请状态" width="100"/>
        <el-table-column prop="applyDatetime" label="申请时间" width="180"/>
        <el-table-column prop="reviewComments" label="审批意见" width="100"/>
        <el-table-column prop="reviewDatetime" label="审批时间" width="180"/>
      </el-table>
    </div>

  <!-- 弹出框 表单 -->
  <el-dialog v-model="dialogFormVisible" title="审批">
    <el-form ref="dialogForm" :rules="rules" :model="form">
      <el-form-item label="申请id" prop="applyId" v-show="false">
        <el-input v-model="form.applyId" autocomplete="off"/>
      </el-form-item>
      <el-form-item label="申请编号" prop="applyNum" :label-width="formLabelWidth">
        <el-input disabled v-model="form.applyNum" autocomplete="off"/>
      </el-form-item>
      <el-form-item label="申请天数" prop="applyDay" :label-width="formLabelWidth">
        <el-input disabled v-model="form.applyDay" autocomplete="off"/>
      </el-form-item>
      <el-form-item label="申请原因" prop="applyReason" :label-width="formLabelWidth">
        <el-input disabled v-model="form.applyReason" autocomplete="off"/>
      </el-form-item>
      <el-form-item  label="申请时间" prop="applyDatetime" :label-width="formLabelWidth">
        <el-date-picker disabled v-model="form.applyDatetime" format="YYYY-MM-DD HH:mm:ss"
                        value-format="YYYY-MM-DD HH:mm:ss"
                        type="datetime" laceholder="选择时间"/>
      </el-form-item>
      <el-form-item label="审批状态" prop="applyStatus" :label-width="formLabelWidth" v-show="false">
        <el-input disabled v-model="form.applyStatus" autocomplete="off"/>
<!--        <el-select v-model="form.applyStatus" placeholder="请选择">-->
<!--          <el-option v-for="item in applyStatusOpt" :key="item.value" :label="item.label"-->
<!--                     :value="item.value"/>-->
<!--        </el-select>-->
      </el-form-item>
      <el-form-item label="审批意见" prop="reviewComments" :label-width="formLabelWidth">
                <el-select v-model="form.reviewComments" placeholder="请选择">
                  <el-option v-for="item in reviewOpt" :key="item.value" :label="item.label"
                             :value="item.value"/>
                </el-select>
      </el-form-item>
      <el-form-item label="审批时间" prop="reviewDatetime" :label-width="formLabelWidth" v-show="true">
        <el-input disabled v-model="form.reviewDatetime" autocomplete="off"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="handleCancel">取消</el-button>
        <el-button type="primary" @click="handleSubmit"> 提交 </el-button>
      </span>
    </template>
  </el-dialog>
  <!-- 弹出框 表单 -->
    <!-- 分页 -->
    <el-pagination style="margin-top:20px" :current-page="searchForm.pageNum" :page-size="searchForm.pageSize"
                   :page-sizes="[10, 20, 30, 40]" layout="->,total, sizes, prev, pager, next, jumper" :total="total"
                   @size-change="handleSizeChange" @current-change="handleCurrentChange"/>
  </el-card>
</template>

<script setup>
import {ref, reactive} from 'vue'
import apply from "../../api/apply.js";
import {ElMessage, ElMessageBox} from "element-plus"
import {Delete, Edit, Search, Download, Plus, Refresh} from '@element-plus/icons-vue'
import {add} from "lodash";
import FileSaver from 'file-saver';
import * as XLSX from "xlsx";


const usersId = ref("");

usersId.value=sessionStorage.getItem("userId");
console.log(usersId.value);
//搜索框响应
const queryParams = reactive({
  // userId: usersId.value,
  userName: undefined,
  applyStatus: undefined

})

// //页码
// const pagination = ref({
//   current: 1,
//   size: 10,
//   total: 0
// })
// 搜索条件
const searchForm = reactive({
  pageNum: 1,
  pageSize: 10,
  //oldUserId:userId,
  labName: '',
  applyStatus: 3
})
let total = ref(0);
const handleSizeChange = (pageSize) => {
  searchForm.pageSize = pageSize;
  getData();
}
const handleCurrentChange = (pageNum) => {
  searchForm.pageNum = pageNum;
  getData();
}
const searchApply = () => {
  searchForm.pageNum = 1;
  getData();
}
// 申请状态的集合
const applyStatusOpt = [
  {value: 3, label: '待审批'},
  {value: 1, label: '已驳回'},
  {value: 2, label: '已通过'}
]
// 审批集合
const reviewOpt = [
  {value: 1, label: '不同意'},
  {value: 2, label: '同意'}
]
// 格式化列
const formatter = (row, column, cellValue, index) => {
  if (cellValue === 3) {
    return "待审批";
  } else if (cellValue === 2) {
    return "审批完成";
  } else if (cellValue === 1) {
    return "审批完成";
  }
}

//处理分页组件中当前页码变化的事件
const handleCurrentPage = (currentPage) => {
  console.log(currentPage)
  searchForm.pageNum = currentPage
  getData()
}

// 处理分页组件中每页显示记录数变化的事件
const handlePageSize = () => {
}


// 定义表格数据对象
const tableData = ref([])
//  定义初始化数据方法
const getData = async () => {

  // 更新查询参数的当前页和每页大小
  queryParams.pageNum = 1
  queryParams.pageSize = 10
  queryParams.userId = usersId.value
// 测试向表格数据中添加一条额外的记录
//   tableData.value.push({
//
//     applyReason: '上课',
//     applyStatus: 0,
//
//   });
  // 发送请求获取实验申请列表数据
  let res = await apply.getReviewList(queryParams)
  // 打印获取到的响应数据
  console.log(res.data)
  // 更新分页信息
  searchForm.pageNum = res.data.data.pageNum
  searchForm.pageSize = res.data.data.pageSize
  total.value = res.data.total;
  //res.data是res的data对象
  // 更新表格数据
  tableData.value = res.data.data
}
// 执行初始化数据方法
getData();


// 查询条件
const handleQuery = () => {
  getData()
}

// 重置
const handleReset = () => {
  queryParams.userName = undefined
  queryParams.applyStatus = undefined
  handleQuery()
}

// 弹出框可见性.默认为隐藏
const dialogFormVisible = ref(false)

const formLabelWidth = '100px'

// 表单数据对象
const form = ref({
  applyId: '',
  applyNum: '',
  applyDay: '',
  applyStatus: '',
  applyReason: '',
  applyDatetime: ''
})
// 表单对象,用于数据校验
const dialogForm = ref(null)
// 表单校验规则
const rules = {
  applyStatus: [{required: true, message: "请选择审批状态", trigger: "blur"}],
  reviewComments: [{required: true, message: "请输入审批意见", trigger: "blur"}],
}

// 重置表单数据
const resetFormValue = () => {
  form.value = {
    applyNum: '',
    applyDay: '',
    applyReason: '',
    applyDatetime: '',
    applyStatus: '',
    reviewComments: '',
    reviewDatetime: ''
  }
}

// 弹出框提交
const handleSubmit = () => {
  // 如果applyId存在，是编辑，否则新增
  console.log(form.value)
  if (form.value.applyId) {
    edit()
  } else {
    add()
  }
}

// 弹出框取消
const handleCancel = () => {
  dialogFormVisible.value = false
  resetFormValue()
}

// 审核按钮
const handleEdit = (index, row) => {
  dialogFormVisible.value = true
  getApplyById(row.applyId)
}

const applyParams = reactive({
  applyId: undefined,
  userId: undefined
})

// 根据applyId，获取数据
const getApplyById = (applyId) => {
  applyParams.applyId = applyId
  applyParams.userId = usersId.value

  apply.getReviewDetail(applyParams).then(
      res => {
        if (res.status === 200) {
          form.value = res.data.data[0]
        }
      }
  )
}

// 审核
const edit = () => {
  dialogForm.value.validate(
      (valid) => {
        if (valid) {
          // 请求后台,新增数据
          apply.reviewApply(form.value).then(
              res => {
                if (res.data.code === 200) {
                  dialogFormVisible.value = false
                  ElMessage({
                    message: res.data.message,
                    type: "success",
                  })
                  location.reload()
                  // 刷新页面
                  getData()
                  // 重置表单数据
                  resetFormValue()
                } else {
                  ElMessage({
                    message: res.data.message,
                    type: "error",
                  })
                }
              }
          )
        } else {
          console.log("校验不通过")
        }
      }
  )
}

// // 删除
// const handleDelete = (index, row) => {
//
//   ElMessageBox.confirm(
//       '确定删除该信息么',
//       '提示',
//       {
//         confirmButtonText: '确认',
//         cancelButtonText: '取消',
//         type: 'error',
//       }
//   ).then(() => {
//     apply.delApply({params: {applyId: row.applyId}}).then(res => {
//       if (res.data.code === 200) {
//         dialogFormVisible.value = false
//         ElMessage({
//           message: res.data.message,
//           type: "success",
//         })
//         // 刷新页面
//         getData()
//       } else {
//         ElMessage({
//           message: res.data.message,
//           type: "error",
//         })
//       }
//     })
//   }).catch(() => {
//     ElMessage({
//       type: 'info',
//       message: '取消',
//     })
//   })
// }
// excel导出
const handleExport = () => {
  ElMessageBox.confirm(
      '确定导出数据么',
      '提示',
      {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'error',
      }
  ).then(() => {
    //会默认使用科学计数法表示，则需要这样设置
    var wb = XLSX.utils.table_to_book(
        document.querySelector("#table-export"),
        {raw: true}
    );
    // 获取二进制字符串作为输出
    var wbout = XLSX.write(wb, {
      bookType: 'xlsx',
      book: true,
      type: 'array',
    })
    try {
      FileSaver.saveAs(
          // Blob: 对象表示一个不可变 原始数据的类文件对象,不一定是JS原生格式的数据。
          // File: 基于Blob，继承了blob的功能并将其扩展使其支持用户系统上的文件。
          new Blob([wbout], {type: 'appliction/octet-stream'}),
          // 设置导出的文件名称可随意
          '审批结果.xlsx'
      )
    } catch (e) {
      if (typeof console != 'undefined') console.log(e, wbout);
    }
    // 返回一个新创建的Blob对象，其内容由参数中给定的数组串联组成。
    return wbout
  }).catch(() => {
    ElMessage({
      type: 'info',
      message: '取消',
    })
  })
}



</script>

<style></style>
