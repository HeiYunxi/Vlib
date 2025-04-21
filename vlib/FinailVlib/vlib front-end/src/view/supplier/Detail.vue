<template>
  <div>
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>供应商详情</span>&nbsp;&nbsp;&nbsp;
          <el-button size="mini" style="float:right" @click="router.go(-1)">返回</el-button>
        </div>
      </template>
      <el-form :model="supplierDetail" :rules="rules" ref="ruleFormRef">
        <el-form-item label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ID:">
          <el-input v-model="supplierDetail.supplierId" style="width:300px" :readonly="true"
                    class="input-with-select"></el-input>
        </el-form-item>


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

        <p v-if="route.query.roleId=='' || route.query.edit=='1'">
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
  if (route.query.supplierId) {
    const res = await userApi.getSupplierDetail({supplierId: route.query.supplierId})
    console.log(res.data.data);
    supplierInfo.value = res.data.data;

    supplierDetail.supplierId = res.data.data[0].supplierId;
    supplierDetail.supplierName = res.data.data[0].supplierName;
    supplierDetail.contact = res.data.data[0].contact;
    supplierDetail.phone = res.data.data[0].phone;
    supplierDetail.email = res.data.data[0].email;
    supplierDetail.address = res.data.data[0].address;
  }
})
const ruleFormRef = ref();
const rules = reactive({
  supplierName: [{required: true, message: "供应商名称不能为空", trigger: "blur"}],
});
const onSubmit = () => {
  if (!ruleFormRef) return;
  ruleFormRef.value.validate(async (valid) => {
    if (valid) {

      const res = await userApi.updateSupplier(supplierDetail);

      if (res.data != null) {
        if (res.data) {

          ElMessage.info("供应商修改成功");
          router.push("/supplier/list");
        } else {
          ElMessage.error("供应商修改失败");
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