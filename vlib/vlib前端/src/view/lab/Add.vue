<template>
  <div>
    <el-card>
      <template #header>
        <div class="card-header">

          <span>添加实验室</span>
          <el-button size="mini" style="float:right" @click="router.go(-1)">返回</el-button>&nbsp;&nbsp;&nbsp;

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
          <el-input v-model="labDetail.local" style="width:300px" placeholder="请输入位置"
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


          <el-button type="primary" @click="onSubmit()">保存</el-button>
          <el-button type="primary" @click="resetForm()">重置</el-button>
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
  local: '',
  capacity: '',
  area:'',
})
const labInfo = ref([]);
onBeforeMount(async () => {
  labDetail.labName = '';
  labDetail.labNumber = '';
  labDetail.local = '';
  labDetail.capacity = '';
  labDetail.area = '';
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
      const res = await userApi.saveLabInfo(labDetail);
      if (res.data != null) {
        if (res.data) {
          if (res.data.data == 0) {
            ElMessage.error("该实验室已注册");
          } else {
            ElMessage.info("新增实验室成功");
            router.push("/lab/list");
          }
        } else {
          ElMessage.error("新增失败");
        }
      } else {
        ElMessage.error("服务器内部错误");
      }
    } else {
      alert(1);
      return false;
    }
  });
};
const resetForm = () => {
  if (!ruleFormRef) return;
  ruleFormRef.value.resetFields();
};
</script>

<style lang="scss" scoped>

</style>