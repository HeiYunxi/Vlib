<template>
  <div>
    <p>供应商管理</p>
    <el-card>
      <el-input style="width:440px" @clear="searchSupplier" clearable v-model="searchForm.supplierName"
                placeholder="请输入供应商名称" class="input-with-select">
        <template #append>
          <el-button icon="Search" @click="searchSupplier(value)"/>
        </template>
      </el-input>
      &nbsp;&nbsp;&nbsp;
      <el-button type="primary"
                 @click="() => router.push({ path: '/supplier/save', query: { SupplierId:''} })">新增供应商
      </el-button>


      <el-table id="Supplier-table" :data="tableData" border style="width: 100%;margin-top:20px">

        <el-table-column class="table" id="col" prop="supplierId" label="ID" width="50"/>
        <el-table-column class="table" id="col" prop="supplierName" label="供应商名称" width="250"/>
        <el-table-column class="table" id="col" prop="contact" label="联系人" width="100"/>
        <el-table-column class="table" id="col" prop="phone" label="联系电话" width="200"/>
        <el-table-column class="table" id="col" prop="email" label="邮箱" width="200"/>
        <el-table-column class="table" id="col" prop="address" label="地址" width="200"/>

        <el-table-column label="操作" width="430">
          <template #default="scope">
            <el-button type="danger" size="small" @click="deleteSupplier(scope.row.supplierId)">
              删除
            </el-button>
            <el-button size="small" type="primary"
                       @click="() => router.push({ path: '/supplier/detail', query: { supplierId: scope.row.supplierId } })">
              详情
            </el-button>
            <el-button size="small" type="success"
                       @click="() => router.push({ path: '/supplier/detail', query: { supplierId: scope.row.supplierId,edit:1 } })">
              编辑
            </el-button>
            <el-button size="small" type="success" plain
                       @click="showEquipmentDialog(scope.row.supplierId)">查看供应设备
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 -->
      <el-pagination style="margin-top:20px" :current-page="searchForm.pageNum" :page-size="searchForm.pageSize"
                     :page-sizes="[10, 20, 30, 40]" layout="->,total, sizes, prev, pager, next, jumper" :total="total"
                     @size-change="handleSizeChange" @current-change="handleCurrentChange"/>
    </el-card>
    <!-- 角色对应用户列表-->
    <el-dialog
        title="设备列表"
        v-model="equipmentDialogVisible"
        width="700px"
        :before-close="handleClose">
      <div style="text-align:left;">
        <el-table :data="equipmentTableData" border style="width: 100%;margin-top:20px"
                  @selection-change="handleSelectionChange"
                  ref="myTable">

          <el-table-column type="selection"  width="55" />
          <el-table-column prop="equipmentId" label="id" width="180"/>
          <el-table-column prop="equipmentName" label="设备名" width="180"/>
        </el-table>
      </div>

    </el-dialog>

  </div>
</template>

<script setup>
import userApi from "../../api/supplier.js";
import {onMounted, reactive, ref,nextTick} from "vue";
import {ElMessage, ElMessageBox} from 'element-plus';
import {useRouter} from 'vue-router'
import FileSaver from 'file-saver'
import * as XLSX from 'xlsx'

const value = ref(false)

const router = useRouter();
// Dom 挂载之后
onMounted(() => {
  getSupplierList();
})

// 用户数据
let tableData = ref([]);
let total = ref(0);
// 搜索条件
const searchForm = reactive({
  pageNum: 1,
  pageSize: 10,
  flag: false,
  supplierName: ''
})


// 获取供应商列表
const getSupplierList = async () => {
  const res = await userApi.getSupplierList(searchForm);
  console.log(res);
  tableData.value = res.data.data;
  console.log(res.data.data);
  total.value = res.data.total;
}
const handleSizeChange = (pageSize) => {
  searchForm.pageSize = pageSize;
  getSupplierList();
}
const handleCurrentChange = (pageNum) => {
  searchForm.pageNum = pageNum;
  getSupplierList();
}
const searchSupplier = (value) => {
  searchForm.pageNum = 1;
  searchForm.flag = value;
  getSupplierList();
}
// 删除用户ID
const deleteSupplier = (supplierId) => {
  ElMessageBox.confirm(
      '确定要删除该供应商信息吗?',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
  ).then(async () => {
    const res = await userApi.delSupplier({supplierId: supplierId});
    if (res.data) {
      ElMessage.success("删除供应商成功")
      getSupplierList();
    } else {
      ElMessage.error("删除供应商失败")
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
      document.querySelector("#Supplier-table"),
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

/******************用户多选框开始***************************/
const equipmentTableData = ref([]);
let myTable = ref([]);
let selectSupplier = ref([]);//选中的角色
let selectSupplierEquipment = ref([]);//选中的角色对应用户
const equipmentSearchForm = reactive({
  pageNum: 1,
  pageSize: 100,
  equipmentName: '',
  flag:0
})
const equipmentDialogVisible = ref(false);//是否打开用户弹窗

const showEquipmentDialog = async (supplierId) => {
  selectSupplier.value = supplierId;

  const res = await userApi.getEquipmentList(equipmentSearchForm);
  equipmentTableData.value = res.data.data;
  myTable = equipmentTableData.value;
  equipmentDialogVisible.value = true;
  const ress = await userApi.getSupplierSimulationEquipmentList({supplierId: supplierId});
  selectSupplierEquipment = ress.data.data;
  nextTick(() => {

    for (const equipment in ress.data.data) {

      for (const equipments in res.data.data) {

        if (res.data.data[equipments].equipmentId == ress.data.data[equipment].equipmentId) {
          myTable.toggleRowSelection(equipmentTableData.value[equipments], true); // 设置为选中状态
        }
      }
    }
  });
};

const multipleTableRef = ref();
const multipleSelection = ref([])
const handleSelectionChange = (selection) => {
  // 处理选中项变化

  selectSupplierEquipment = selection;
  console.log(selectSupplierEquipment);
};
const handleEquipmentConfirm = async () => {

  console.log(selectSupplierEquipment);

  //保存角色所属用户
  const ress = await userApi.saveSupplierSimulationEquipmentList({
    supplierId: selectSupplier.value,
    equipmentList: selectSupplierEquipment
  });
  if (ress.data) {
    ElMessage.success("保存供应商设备成功");
  } else {
    ElMessage.error("保存供应商设备失败");
  }
  equipmentDialogVisible.value = false;
};
/******************用户多选框结束***************************/

</script>

<style lang="scss" scoped>


</style>