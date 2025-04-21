<template>
  <div>
    <p>实验室管理</p>
    <el-card>
      <el-input style="width:440px" @clear="searchLab" clearable v-model="searchForm.labName"
                placeholder="请输入实验室名称" class="input-with-select">
        <template #append>
          <el-button icon="Search" @click="searchLab()"/>
        </template>
      </el-input>
      &nbsp;&nbsp;&nbsp;
      <el-button type="primary"
                 @click="() => router.push({ path: '/lab/save', query: { labId:''} })">新增实验室
      </el-button>
      <el-button class="btn-seal" @click="exportExcel">导出结果</el-button>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<!--      <span class="device-text">查看可用实验室</span>-->
<!--      <el-switch-->
<!--          v-model="value"-->
<!--          active-color="#13ce66"-->
<!--          inactive-color="#ff4949"-->
<!--          @click="searchLab(value)">-->
<!--      </el-switch>-->


      <el-table id="Lab-table" :data="tableData" border style="width: 100%;margin-top:20px">

        <el-table-column class="table" id="col" prop="labId" label="ID" width="50"/>
        <el-table-column class="table" id="col" prop="labNumber" label="实验室编号" width="150"/>
        <el-table-column class="table" id="col" prop="labName" label="实验室名称" width="150"/>
        <el-table-column class="table" id="col" prop="position" label="位置" width="80"/>
        <el-table-column class="table" id="col" prop="capacity" label="容量" width="100"/>
        <el-table-column class="table" id="col" prop="area" label="面积" width="80"/>
        <el-table-column class="table" id="col" prop="managerName" label="管理教师" width="100"/>
        <el-table-column label="操作" width="430">
          <template #default="scope">
            <el-button type="danger" size="small" @click="deleteLab(scope.row.labId)">
              删除
            </el-button>
            <el-button size="small" type="primary"
                       @click="() => router.push({ path: '/lab/detail', query: { labId: scope.row.labId } })">
              详情
            </el-button>
            <el-button size="small" type="success"
                       @click="() => router.push({ path: '/lab/detail', query: { labId: scope.row.labId,edit:1 } })">
              编辑
            </el-button>

          </template>
        </el-table-column>
      </el-table>

      <!--不显示，用户导出-->
      <div v-show="false">
      <el-table id="Lab-table-export" :data="tableData" border style="width: 100%;margin-top:20px">
        <el-table-column class="table" id="col" prop="labId" label="ID" width="50"/>
        <el-table-column class="table" id="col" prop="labNumber" label="实验室编号" width="150"/>
        <el-table-column class="table" id="col" prop="labName" label="实验室名称" width="150"/>
        <el-table-column class="table" id="col" prop="position" label="位置" width="80"/>
        <el-table-column class="table" id="col" prop="capacity" label="容量" width="100"/>
        <el-table-column class="table" id="col" prop="area" label="面积" width="80"/>
        <el-table-column class="table" id="col" prop="managerName" label="管理教师" width="100"/>
      </el-table>
      </div>

      <!-- 分页 -->
      <el-pagination style="margin-top:20px" :current-page="searchForm.pageNum" :page-size="searchForm.pageSize"
                     :page-sizes="[10, 20, 30, 40]" layout="->,total, sizes, prev, pager, next, jumper" :total="total"
                     @size-change="handleSizeChange" @current-change="handleCurrentChange"/>
    </el-card>


  </div>
</template>

<script setup>
import userApi from "../../api/lab.js";
import {onMounted, reactive, ref} from "vue";
import {ElMessage, ElMessageBox} from 'element-plus';
import {useRouter} from 'vue-router'
import FileSaver from 'file-saver'
import * as XLSX from 'xlsx'

const value = ref(false)

const router = useRouter();
// Dom 挂载之后
onMounted(() => {
  getLabList();
})

// 用户数据
let tableData = ref([]);
let total = ref(0);
// 搜索条件
const searchForm = reactive({
  pageNum: 1,
  pageSize: 10,
  labName: ''
})


// 获取实验室列表
const getLabList = async () => {
  const res = await userApi.getLabList(searchForm);
  console.log(res);
  tableData.value = res.data.data;
  console.log(res.data.data);
  total.value = res.data.total;
}
const handleSizeChange = (pageSize) => {
  searchForm.pageSize = pageSize;
  getLabList();
}
const handleCurrentChange = (pageNum) => {
  searchForm.pageNum = pageNum;
  getLabList();
}
const searchLab = () => {
  searchForm.pageNum = 1;
  getLabList();
}
// 删除用户ID
const deleteLab = (labId) => {
  ElMessageBox.confirm(
      '确定要删除该实验室信息吗?',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
  ).then(async () => {
    const res = await userApi.delLab({labId: labId});
    if (res.data) {
      ElMessage.success("删除实验室成功")
      getLabList();
    } else {
      ElMessage.error("删除实验室失败")
    }
  }).catch(() => {
    ElMessage({
      type: 'info',
      message: '取消删除',
    })
  })
}


const exportExcel = () => {
  //会默认使用科学计数法表示，则需要这样设置
  var wb = XLSX.utils.table_to_book(
      document.querySelector("#Lab-table-export"),
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
        'result.xlsx'
    )
  } catch (e) {
    if (typeof console != 'undefined') console.log(e, wbout);
  }
  // 返回一个新创建的Blob对象，其内容由参数中给定的数组串联组成。
  return wbout
}


</script>

<style lang="scss" scoped>


</style>