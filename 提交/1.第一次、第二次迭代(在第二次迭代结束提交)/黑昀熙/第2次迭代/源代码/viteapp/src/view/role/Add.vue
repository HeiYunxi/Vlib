<template>
    <div>
        <el-card >
            <template #header>
                <div class="card-header">

                    <span>角色信息</span>
                    <el-button size="mini" style="float:right" @click="router.go(-1)">返回</el-button>&nbsp;&nbsp;&nbsp;

                </div>
            </template>
            <el-form :model="roleDetail" :rules="rules" ref="ruleFormRef">
                <p v-if="route.query.edit=='1'">
                <el-form-item label="编号:">
                 {{ route.query.roleId }}
                </el-form-item>
                </p>
                <p v-else>
                </p>
                <el-form-item label="角&nbsp;&nbsp;&nbsp;&nbsp;色:">
                <el-input v-model="roleDetail.roleName" style="width:300px" placeholder="请输入角色名称" class="input-with-select"></el-input>
                </el-form-item>

              <el-form-item label="描&nbsp;&nbsp;&nbsp;&nbsp;述:">
                <el-input v-model="roleDetail.description" style="width:300px" placeholder="请输入角色描述" class="input-with-select"></el-input>
              </el-form-item>


              <el-button type="primary" @click="onSubmit()">保存</el-button>
              <el-button type="primary" @click="resetForm()">重置</el-button>
            </el-form>
        </el-card>
    </div>
</template>

<script setup>
import { onBeforeMount, reactive, ref } from 'vue';
import { useRouter, useRoute } from 'vue-router'
import userApi from "../../api/role";
import {ElMessage} from "element-plus";
const route = useRoute();
const router = useRouter();
const roleDetail = reactive({
    roleId:'',roleName:'',description:''
})
const userInfo=ref([]);
onBeforeMount(async () => {
  roleDetail.roleName ='';
  roleDetail.description ='';
})
const ruleFormRef = ref();
const rules = reactive({
  roleName: [{ required: true, message: "角色名称不能为空", trigger: "blur" }],
  description: [{ required: true, message: "描述不能为空", trigger: "blur" }],
});
const onSubmit = () => {
  if (!ruleFormRef) return;
  ruleFormRef.value.validate(async (valid) => {
    if (valid) {
      const res = await userApi.saveRoleInfo(roleDetail);

      if (res.data!=null) {
        if (res.data) {
          ElMessage.info("新增角色成功");
          router.push("/role/list");
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