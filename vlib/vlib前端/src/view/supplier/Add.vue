<template>
  <div>
    <el-card>
      <template #header>
        <div class="card-header">

          <span>添加供应商</span>
          <el-button size="mini" style="float:right" @click="router.go(-1)">返回</el-button>&nbsp;&nbsp;&nbsp;

        </div>
      </template>
      <el-form :model="supplierDetail" :rules="rules" ref="ruleFormRef">
        <p v-if="route.query.edit=='1'">
          <el-form-item label="编号:">
            {{ route.query.userId }}
          </el-form-item>
        </p>
        <p v-else>
        </p>


        <el-form-item label="供应商名称:">
          <el-input v-model="supplierDetail.supplierName" style="width:300px"
                    placeholder="请输入供应商名称" class="input-with-select"></el-input>
        </el-form-item>


        <el-form-item label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系人:">
          <el-input v-model="supplierDetail.contact" style="width:300px" placeholder="请输入联系人"
                    class="input-with-select"></el-input>
        </el-form-item>
        <el-form-item label="&nbsp;&nbsp联系电话:">
          <el-input v-model="supplierDetail.phone" style="width:300px" placeholder="请输入容量"
                    class="input-with-select"></el-input>
        </el-form-item>

        <el-form-item label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮箱:">
          <el-input v-model="supplierDetail.email" style="width:300px" placeholder="请输入邮箱"
                    class="input-with-select"></el-input>
        </el-form-item>

        <el-form-item label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地址:">
          <el-input v-model="supplierDetail.address" style="width:300px" placeholder="请输入地址"
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
import userApi from "../../api/supplier";
import {ElMessage} from "element-plus";

const route = useRoute();
const router = useRouter();
const supplierDetail = reactive({
  supplierId: '',
  supplierName: '',
  contact: '',
  phone: '',
  email:'',
  address:'',
})
const supplierInfo = ref([]);
onBeforeMount(async () => {
  supplierDetail.supplierName = '';
  supplierDetail. contact = '';
  supplierDetail.phone = '';
  supplierDetail.email = '';
  supplierDetail.address = '';
})
const ruleFormRef = ref();
const rules = reactive({
  supplierNumber: [{required: true, message: "供应商编号不能为空", trigger: "blur"}],
  supplierName: [{required: true, message: "供应商名称不能为空", trigger: "blur"}],
});
const onSubmit = () => {
  if (!ruleFormRef) return;
  ruleFormRef.value.validate(async (valid) => {
    if (valid) {
      const res = await userApi.saveSupplierInfo(supplierDetail);
      if (res.data != null) {
        if (res.data) {
          if (res.data.data == 0) {
            ElMessage.error("该供应商已注册");
          } else {
            ElMessage.info("新增供应商成功");
            router.push("/supplier/list");
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