<template>
  <div>
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>角色详情</span>&nbsp;&nbsp;&nbsp;
          <el-button size="mini" style="float:right" @click="router.go(-1)">返回</el-button>
        </div>
      </template>
      <el-form :model="roleDetail" :rules="rules" ref="ruleFormRef">
        <el-form-item label="角色ID:">
          <el-input v-model="roleDetail.roleId" style="width:300px" :readonly="true"
                    class="input-with-select"></el-input>
        </el-form-item>

        <el-form-item label="角色名称:">
          <el-input v-model="roleDetail.roleName" style="width:300px" placeholder="请输入角色名称"
                    class="input-with-select"></el-input>

        </el-form-item>
        <el-form-item label="角色描述:">
          <el-input v-model="roleDetail.description" style="width:300px" placeholder="请输入角色描述"
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
import userApi from "../../api/role";
import {ElMessage} from "element-plus";

const route = useRoute();
const router = useRouter();
const roleDetail = reactive({
  roleId: '', roleName: '', description: ''
})
const roleInfo = ref([]);
onBeforeMount(async () => {
  if (route.query.roleId) {
    const res = await userApi.getRoleDetail({roleId: route.query.roleId})
    console.log(res.data.data);
    roleInfo.value = res.data.data;
    roleDetail.roleName = res.data.data[0].roleName;
    roleDetail.description = res.data.data[0].description;
    roleDetail.roleId = res.data.data[0].roleId;

  }
})
const ruleFormRef = ref();
const rules = reactive({
  roleName: [{required: true, message: "角色名称不能为空", trigger: "blur"}],
  description: [{required: true, message: "角色描述不能为空", trigger: "blur"}],
});
const onSubmit = () => {
  if (!ruleFormRef) return;
  ruleFormRef.value.validate(async (valid) => {
    if (valid) {

      const res = await userApi.updateRole(roleDetail);

      if (res.data != null) {
        if (res.data) {

          ElMessage.info("角色修改成功");
          router.push("/role/list");
        } else {
          ElMessage.error("角色修改失败");
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