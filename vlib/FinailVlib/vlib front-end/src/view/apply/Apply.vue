<template>
  <div>
    <p>实验室申请</p>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-form-item label="实验室名称">
<!--            <el-input @clear="searchApply" clearable v-model="searchForm.labName"-->
<!--                      placeholder="请输入实验室名称" class="input-with-select">-->
<!--            </el-input>-->
            <el-input style="width:440px" @clear="searchApply" clearable v-model="searchForm.labName"
                      placeholder="请输入实验室名称" class="input-with-select">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="申请状态">
            <el-select v-model="searchForm.applyStatus" style="width:300px;" placeholder="请选择状态" clearable>
              <el-option v-for="item in applyStatusOpt" :label="item.label" :value="item.value" :key="item.value"></el-option>
            </el-select>
          </el-form-item>

        </el-col>
        <el-col :span="6">
          <el-form-item>
            <el-button :icon="Search" type="primary" @click="searchApply">查询</el-button>
          </el-form-item>
        </el-col>
      </el-row>
      <!-- 工具栏 -->
      <el-row>
        <el-col :span="2">
          <el-button type="primary" @click="dialogFormVisible=true;dialogTitle='新增申请'">新增申请</el-button>

        </el-col>

      </el-row>
      <!-- 工具栏 -->
      &nbsp;&nbsp;&nbsp;
      <!-- 列表 -->
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="applyId" label="申请编号" width="150"/>
        <el-table-column prop="labName" label="实验室名称" width="150"/>
        <el-table-column prop="applyReason" label="实验室申请原因" width="150"/>
        <el-table-column prop="applyStatus" label="实验室申请状态" width="150"/>
        <el-table-column prop="applyDatetime" label="实验室申请时间" width="200"/>
        <el-table-column prop="reviewComments" label="申请审批意见" width="150"/>
        <el-table-column prop="reviewDatetime" label="申请审批时间" width="200"/>
        <!-- 列表 -->
        <el-table-column label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作" width="120">
          <template #default="scope">

            <el-button type="primary" :icon="Edit" @click="handleEdit(scope.$index, scope.row)" circle/>
            <el-button type="danger" :icon="Delete" @click="cancel(scope.row.applyId)" circle/>


          </template>
        </el-table-column>
      </el-table>
      <!-- 分页 -->
      <el-pagination style="margin-top:20px" :current-page="searchForm.pageNum" :page-size="searchForm.pageSize"
                     :page-sizes="[10, 20, 30, 40]" layout="->,total, sizes, prev, pager, next, jumper" :total="total"
                     @size-change="handleSizeChange" @current-change="handleCurrentChange"/>
    </el-card>
  </div>

  <!-- 弹出框 表单 -->
  <el-dialog v-model="dialogFormVisible" :title="dialogTitle">
    <el-form :model="ApplyForm" :rules="rules" ref="ruleFormRef">
      <el-form-item label="申请id" prop="applyId" v-show="false">
        <el-input v-model="ApplyForm.applyId" autocomplete="off"/>
      </el-form-item>
      <el-form-item label="实验室编号:" prop="labNumber" :label-width="formLabelWidth">
        <el-input v-model="ApplyForm.labNumber" style="width:300px"
                  placeholder="请输入实验室编号" class="input-with-select"></el-input>
      </el-form-item>

      <el-form-item label="实验室名称:" prop="labName" :label-width="formLabelWidth">
        <el-input v-model="ApplyForm.labName" style="width:300px" placeholder="请输入实验室名称"
                  class="input-with-select"></el-input>
      </el-form-item>

      <el-form-item label="申请天数:" prop="applyDay" :label-width="formLabelWidth">
        <el-input v-model="ApplyForm.applyDay" style="width:300px" placeholder="请输入申请天数"
                  class="input-with-select"></el-input>
      </el-form-item>

      <el-form-item label="申请原因:" prop="applyReason" :label-width="formLabelWidth">
        <el-input v-model="ApplyForm.applyReason" style="width:300px" placeholder="请输入申请原因"
                  class="input-with-select"></el-input>
      </el-form-item>

      <el-form-item label="申请时间:" prop="applyDatetime" :label-width="formLabelWidth">
        <el-date-picker v-model="ApplyForm.applyDatetime" style="width:300px"  format="YYYY-MM-DD HH:mm:ss" value-format="YYYY-MM-DD HH:mm:ss"
                        type="datetime" laceholder="选择时间"/>
      </el-form-item>

      <el-form-item label="审核状态:" :label-width="formLabelWidth" v-show="false">
        <el-select disabled v-model="ApplyForm.applyStatus" style="width:300px;" placeholder="请选择状态"
                   :selected="0">
          <el-option label="可预约" value="1"></el-option>
          <el-option label="已预约" value="2"></el-option>
          <el-option label="不可用" value="3"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="审核意见:" :label-width="formLabelWidth" v-show="false">
        <el-input disabled v-model="ApplyForm.reviewComments" style="width:300px" placeholder="请输入审核意见"
                  class="input-with-select"></el-input>
      </el-form-item>
      <el-form-item label="审批时间:" :label-width="formLabelWidth" v-show="false">
        <el-input disabled v-model="ApplyForm.reviewDatetime" style="width:300px" placeholder="请输入审批时间"
                  class="input-with-select"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>

        <span class="dialog-footer">
          <el-button type="primary" @click="resetForm()">重置</el-button>
          <el-button type="primary" @click="handleSubmit"> 提交 </el-button>
        </span>

    </template>
  </el-dialog>
</template>

<script setup>
import {Delete, Edit, Refresh, Search,} from '@element-plus/icons-vue'
import userApi from "../../api/apply.js";
import {onBeforeMount, onMounted, reactive, ref} from "vue";
import {ElMessage, ElMessageBox, ElTable} from 'element-plus';
import {useRoute, useRouter} from 'vue-router'

const dialogFormVisible = ref(false);
const router = useRouter();
const route = useRoute();
let userId = ref();
const formLabelWidth = "100px"



const dialogTitle=ref("")
const usersId = ref("");

usersId.value=sessionStorage.getItem("userId");
console.log(usersId.value);
//实验室申请新增or编辑表单数据
const ApplyForm = ref({
  applyId:'',
  labName:'',
  labNumber: '',
  applyDay: '',
  applyReason: '',
  applyDatetime: '',
  applyStatus: '',
  reviewComments: '',
  reviewDatetime: '',
  usersId:usersId.value,
})
//新增的数据校验
const ruleFormRef = ref();
const rules = reactive({
  // labName: [{required: true, message: "实验室名称不能为空", trigger: "blur"}],
  labNumber: [{required: true, message: "实验室编号不能为空", trigger: "blur"}],
  applyDay: [{required: false, message: "老师编号不能为空", trigger: "blur"}],
  applyReason: [{required: false, message: "老师编号不能为空", trigger: "blur"}],
  applyDatetime: [{required: false, message: "老师编号不能为空", trigger: "blur"}],
});

//新增的提交
const onSubmit = () => {

  if (!ruleFormRef) return;
  ruleFormRef.value.validate(async (valid) => {
    if (valid) {
      const res = await userApi.saveApplyInfo(ApplyForm.value);
      if (res.data != null) {
        if (res.data) {
          if (res.data.data == 0) {
            ElMessage.error("该实验室已被申请");
          } else {
            ElMessage.info("新增申请成功");
            // router.push("/lab/apply");

              location.reload();

          }
        } else {
          ElMessage.error("新增申请失败");
        }
      } else {
        ElMessage.error("服务器内部错误");
      }
    } else {
      ElMessage.error("格式错误");
      return false;
    }
  });
};

//新增重置
const resetForm = () => {
  if (!ruleFormRef) return;
  ruleFormRef.value.resetFields();
};

// 弹出框提交
const handleSubmit = () => {
  // 如果appluId存在，是编辑，否则新增
  if (ApplyForm.value.applyId) {
    edit()
  } else {
    onSubmit();
  }
}
const getApplyById = async (applyId) => {
  const res = await userApi.getApplyById(applyId);
  const ApplyForm=res.data.data
}

// 编辑按钮
const handleEdit = async (index, row) => {
  dialogFormVisible.value = true
  const res = await userApi.getApplyDetail({applyId: row.applyId})
  console.log(res.data.data[0]);
  ApplyForm.value = res.data.data[0];

  // ApplyForm.value.labName = res.data.data[0].labName;
  // ApplyForm.value.labNumber = res.data.data[0].labNumber;
  // ApplyForm.value.applyDay = res.data.data[0].applyDay;
  // ApplyForm.value.applyReason = res.data.data[0].applyReason;
  // ApplyForm.value.applyDatetime = res.data.data[0].applyDatetime;
  // ApplyForm.value.applyStatus = res.data.data[0].applyStatus;
  // ApplyForm.value.reviewComments = res.data.data[0].reviewComments;
  // ApplyForm.value.reviewDatetime = res.data.data[0].reviewDatetime;
}
// 申请状态的集合
const applyStatusOpt = [
  {value: 3, label: '待审批'},
  {value: 1, label: '已驳回'},
  {value: 2, label: '已通过'}
]
// 编辑
const edit = () => {
  ruleFormRef.value.validate(
      async (valid) => {
        if (valid) {
          // 请求后台,新增数据
          const res = await userApi.updateApply(ApplyForm.value);
          if (res.data.code === 200) {
            dialogFormVisible.value = false
            ElMessage({
              message: res.data.message,
              type: "success",
            })
            // 刷新页面
            await getApplyList();
            // 重置表单数据
            resetForm()
          } else {
            ElMessage({
              message: res.data.message,
              type: "error",
            })
          }
        } else {
          console.log("校验不通过")
        }
      }
  )
}

const applyInfo = ref([]);
onBeforeMount(async () => {
  console.log(111)
  console.log(route.query.applyId)
  if (null != route.query.userId) {
    userId.value = route.query.userId;
    sessionStorage.setItem("userId", route.query.userId);
  }
  if (null == route.query.userId) {
    userId.value = sessionStorage.getItem("userId");
  }
  applyDetail.userId = userId.value;
  searchForm.userId = userId.value


})

// Dom 挂载之后
onMounted(() => {
  getApplyList();
})


// 用户数据
let tableData = ref([]);

let total = ref(0);

const applyDetail = reactive({
  userId: '',
  labId: ''
})

// 搜索条件
const searchForm = reactive({
  pageNum: 1,
  pageSize: 10,
  //oldUserId:userId,
  labName: '',
  applyStatus: 3
})

//跳转实验室新增页面
const applyAdd = () => {
  router.push({path: '/lab/applySave', query: {labId: 1}})
}
// 获取实验室列表
const getApplyList = async () => {
  const res = await userApi.getApplyList(searchForm);
  console.log(res);

  tableData.value = res.data.data;
  // tableData.value = [{
  //   labId: 1,
  //   teacherId: 2,
  //   applyDay: 5,
  //   applyReason: '想用',
  //   applyDatetime: '2021-01-01',
  //   applyStatus: 2,
  //   reviewComments: '',
  //   reviewDatetime: '',
  // }]
  console.log(res.data.data);
  total.value = res.data.total;
}
const handleSizeChange = (pageSize) => {
  searchForm.pageSize = pageSize;
  getApplyList();
}
const handleCurrentChange = (pageNum) => {
  searchForm.pageNum = pageNum;
  getApplyList();
}
const searchApply = () => {
  searchForm.pageNum = 1;
  getApplyList();
}


// 取消预定
const cancel = async (applyId) => {
  try {

    // 发送取消预订请求
    const res = await userApi.cancelApply({applyId:applyId});

    // 处理响应结果
    if (res.data) {
      ElMessage.success("取消申请成功");
      location.reload();k
    } else {
      ElMessage.error("取消申请失败");
    }
  } catch (error) {
    console.error('请求出错:', error);
    ElMessage.error("取消申请过程中发生错误");
  }
};
</script>

<style lang="scss" scoped></style>