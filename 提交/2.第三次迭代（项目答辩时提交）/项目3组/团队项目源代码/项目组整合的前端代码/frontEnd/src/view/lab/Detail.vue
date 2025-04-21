<template>
  <div>
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>实验室详情</span>&nbsp;&nbsp;&nbsp;
          <el-button size="mini" style="float:right" @click="router.go(-1)">返回</el-button>
        </div>
      </template>
      <el-form :model="labDetail" :rules="rules" ref="ruleFormRef">
        <el-form-item label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ID:">
          <el-input v-model="labDetail.labId" style="width:300px" :readonly="true"
                    class="input-with-select"></el-input>
        </el-form-item>

        <el-form-item label="实验室编号:">
          <el-input v-model="labDetail.labNumber" style="width:300px"
                    placeholder="请输入实验室编号" class="input-with-select"></el-input>
        </el-form-item>

        <el-form-item label="实验室名称:">
          <el-input v-model="labDetail.labName" style="width:300px"
                    placeholder="请输入实验室名称" class="input-with-select"></el-input>
        </el-form-item>


        <el-form-item label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位置:">
          <el-input v-model="labDetail.position" style="width:300px" placeholder="请输入位置"
                    class="input-with-select"></el-input>
        </el-form-item>
        <el-form-item label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;容量:">
          <el-input v-model="labDetail.capacity" style="width:300px" placeholder="请输入容量"
                    class="input-with-select"></el-input>
        </el-form-item>
        <el-form-item label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;面积:">
          <el-input v-model="labDetail.area" style="width:300px" placeholder="请输入面积"
                    class="input-with-select"></el-input>
        </el-form-item>
        <el-form-item label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;教师:">
          <el-select v-model="labDetail.managerId" style="width:300px" placeholder="请选择管理教师" class="input-with-select">
            <el-option v-for="item in selectData" :key="item.userId" :label="item.userName" :value="item.userId"></el-option>
          </el-select>
        </el-form-item>


        <p v-if="route.query.labId=='' || route.query.edit=='1'">
          <el-button type="primary" @click="onSubmit()">保存</el-button>
        </p>
        <p v-else>
        </p>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import {onBeforeMount, reactive, ref} from 'vue';
import {useRouter, useRoute} from 'vue-router'
import userApi from "../../api/lab";
import {ElMessage} from "element-plus";

const route = useRoute();
const router = useRouter();
const labDetail = reactive({
  labId: '',
  labNumber: '',
  labName: '',
  position: '',
  capacity: '',
  area:'',
  managerId:'',
})
const labInfo = ref([]);
const selectData = ref([
]);
onBeforeMount(async () => {
  if (route.query.labId) {
    //获取供应商下拉列表
    const ress = await userApi.getlabUserList({labId: route.query.labId})
    console.log(ress.data.data);
    selectData.value = ress.data.data;
    //获取详情信息
    const res = await userApi.getLabDetail({labId: route.query.labId})
    console.log(res.data.data);
   labInfo.value = res.data.data;

    labDetail.labId = res.data.data[0].labId;
    labDetail.labNumber = res.data.data[0].labNumber;
    labDetail.labName = res.data.data[0].labName;
    labDetail.position = res.data.data[0].position;
    labDetail.capacity = res.data.data[0].capacity;
    labDetail.area = res.data.data[0].area;
    labDetail.managerId = res.data.data[0].managerId;
  }
})
const ruleFormRef = ref();
const rules = reactive({
  labNumber: [{required: true, message: "实验室编号不能为空", trigger: "blur"}],
  labName: [{required: true, message: "实验室名称不能为空", trigger: "blur"}],
});
const onSubmit = () => {
  if (!ruleFormRef) return;
  ruleFormRef.value.validate(async (valid) => {
    if (valid) {

      const res = await userApi.updateLab(labDetail);

      if (res.data != null) {
        if (res.data) {

          ElMessage.info("实验室修改成功");
          router.push("/lab/list");
        } else {
          ElMessage.error("实验室修改失败");
        }
      } else {
        ElMessage.error("服务器内部错误");
      }
    } else {

      return false;
    }
  });
};
</script>

<style lang="scss" scoped>

</style>