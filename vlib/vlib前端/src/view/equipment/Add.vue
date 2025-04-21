<template>
  <div>
    <el-card>
      <template #header>
        <div class="card-header">

          <span>添加仿真设备</span>
          <el-button size="mini" style="float:right" @click="router.go(-1)">返回</el-button>&nbsp;&nbsp;&nbsp;

        </div>
      </template>
      <el-form :model="equipmentDetail" :rules="rules" ref="ruleFormRef">
        <p v-if="route.query.edit=='1'">
          <el-form-item label="仿真设备编号:">
            {{ route.query.equipmentId }}
          </el-form-item>
        </p>
        <p v-else>
        </p>
        <el-form-item label="仿真设备名称:">
          <el-input v-model="equipmentDetail.equipmentName" style="width:300px"
                    placeholder="请输入仿真设备名称" class="input-with-select"></el-input>
        </el-form-item>
        <el-form-item label="仿真设备编号:">
          <el-input v-model="equipmentDetail.equipmentNumber" style="width:300px"
                    placeholder="请输入仿真设备编号" class="input-with-select"></el-input>
        </el-form-item>
        <el-form-item label="用途:">
          <el-input v-model="equipmentDetail.purpose" style="width:300px" placeholder="请输入用途"
                    class="input-with-select"></el-input>
        </el-form-item>
        <el-form-item label="软件系统:">
          <el-input v-model="equipmentDetail.software_system" style="width:300px" placeholder="请输入软件系统"
                    class="input-with-select"></el-input>
        </el-form-item>
        <el-form-item label="软件版本:">
          <el-input v-model="equipmentDetail.software_version" style="width:300px" placeholder="请输入软件版本"
                    class="input-with-select"></el-input>
        </el-form-item>



        <el-form-item label="状态：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp">
          <el-select v-model="equipmentDetail.status" style="width:300px;" placeholder="请选择状态" :selected="0">
            <el-option label="可用" value="1" ></el-option>
            <el-option label="正在维护" value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;供&nbsp;&nbsp;应&nbsp;&nbsp;商:">
          <el-select v-model="equipmentDetail.supplierId" style="width:300px" placeholder="请选择供应商" :selected="0">
            <el-option v-for="item in selectData" :label="item.supplierName" :value="item.supplierId"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类型:">
          <el-select v-model="equipmentDetail.type" style="width:300px" placeholder="请输入类型">
            <el-option label="教学设备" value="1"></el-option>
            <el-option label="演示设备" value="2"></el-option>
          </el-select>
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
import userApi from "../../api/equipment";
import {ElMessage} from "element-plus";

const route = useRoute();
const router = useRouter();
const equipmentDetail = reactive({
  equipmentId: '',
  equipmentName: '',
  equipmentNumber: '',
  purpose: '',
  software_system: '',
  software_version:'',
  status: '',
  supplierId: '',
  thumbnail: '',
  type: ''
})
const equipmentInfo = ref([]);
onBeforeMount(async () => {
  //获取供应商下拉列表
  const ress = await userApi.getSupplierEquipmentList({equipmentId: 1})
  console.log(ress.data.data);
  selectData.value = ress.data.data;

  equipmentDetail.equipmentName = '';
  equipmentDetail.equipmentNumber = '';
  equipmentDetail.purpose = '';
  equipmentDetail.software_system = '';
  equipmentDetail.software_version = '';
  equipmentDetail.status = '';
  equipmentDetail.supplier = '';
  equipmentDetail.thumbnail = '';
  equipmentDetail.type = '';
})
const ruleFormRef = ref();
const rules = reactive({
  equipmentName: [{required: true, message: "仿真设备名称不能为空", trigger: "blur"}],
  equipmentNumber: [{required: true, message: "仿真设备编号不能为空", trigger: "blur"}],
});
const onSubmit = () => {
  if (!ruleFormRef) return;
  ruleFormRef.value.validate(async (valid) => {
    if (valid) {
      const res = await userApi.saveSimulationEquipmentInfo(equipmentDetail);
      if (res.data != null) {
        if (res.data) {
          if (res.data.data == 0) {
            ElMessage.error("该设备已注册");
          } else {
            ElMessage.info("新增仿真设备成功");
            router.push("/equipment/list");
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
const selectData = ref([
]);
const resetForm = () => {
  if (!ruleFormRef) return;
  ruleFormRef.value.resetFields();
};
</script>

<style lang="scss" scoped>

</style>