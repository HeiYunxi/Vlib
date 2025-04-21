<template>
  <div>
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>仿真设备详情</span>&nbsp;&nbsp;&nbsp;
          <el-button size="mini" style="float:right" @click="router.go(-1)">返回</el-button>
        </div>
      </template>
      <el-form :model="equipmentDetail" :rules="rules" ref="ruleFormRef">
        <el-form-item label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ID:">
          <el-input v-model="equipmentDetail.equipmentId" style="width:300px" :readonly="true"
                    class="input-with-select"></el-input>
        </el-form-item>

        <el-form-item label="仿真设备名称:">
          <el-input v-model="equipmentDetail.equipmentName" style="width:300px"
                    placeholder="请输入仿真设备名称" class="input-with-select"></el-input>
        </el-form-item>
        <el-form-item label="仿真设备编号:">
          <el-input v-model="equipmentDetail.equipmentNumber" style="width:300px"
                    placeholder="请输入仿真设备编号" class="input-with-select"></el-input>
        </el-form-item>
        <el-form-item label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用&nbsp;&nbsp;&nbsp;&nbsp;途:">
          <el-input v-model="equipmentDetail.purpose" style="width:300px" placeholder="请输入用途"
                    class="input-with-select"></el-input>
        </el-form-item>
        <el-form-item label="软&nbsp;&nbsp;件&nbsp;&nbsp;系&nbsp;&nbsp;统:">
          <el-input v-model="equipmentDetail.software_system" style="width:300px" placeholder="请输入软件系统"
                    class="input-with-select"></el-input>
        </el-form-item>
        <el-form-item label="软&nbsp;&nbsp;件&nbsp;&nbsp;版&nbsp;&nbsp;本:">
          <el-input v-model="equipmentDetail.software_version" style="width:300px" placeholder="请输入软件版本"
                    class="input-with-select"></el-input>
        </el-form-item>



        <el-form-item label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp状态：">
          <el-select v-model="equipmentDetail.status" style="width:300px;" placeholder="请选择状态"  >
            <el-option label="可用" value="1" ></el-option>
            <el-option label="正在维护" value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;供&nbsp;&nbsp;应&nbsp;&nbsp;商:">
          <el-select v-model="equipmentDetail.supplierId" style="width:300px" placeholder="请选择供应商" class="input-with-select">
            <el-option v-for="item in selectData" :key="item.supplierId" :label="item.supplierName" :value="item.supplierId"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类型:">
          <el-select v-model="equipmentDetail.type" style="width:300px" placeholder="请选择类型" class="input-with-select">
            <el-option  label="教学设备" value="1"></el-option>
            <el-option label="物理设备" value="2"></el-option>
          </el-select>
        </el-form-item>

        <p v-if="route.query.equipmentId=='' || route.query.edit=='1'">
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
  software_version: '',
  status: '',
  supplierId: '',
  thumbnail: '',
  type: ''
})
const equipmentInfo = ref([]);
const selectData = ref([
]);
onBeforeMount(async () => {
  if (route.query.equipmentId) {
    //获取供应商下拉列表
    const ress = await userApi.getSupplierEquipmentList({equipmentId: route.query.equipmentId})
    console.log(ress.data.data);
    selectData.value = ress.data.data;
    //获取详情信息
    const res = await userApi.getSimulationEquipmentDetail({equipmentId: route.query.equipmentId})
    console.log(res.data.data);
    equipmentInfo.value = res.data.data;

    equipmentDetail.equipmentId = res.data.data[0].equipmentId;
    equipmentDetail.equipmentName = res.data.data[0].equipmentName;
    equipmentDetail.equipmentNumber = res.data.data[0].equipmentNumber;
    equipmentDetail.purpose = res.data.data[0].purpose;
    equipmentDetail.software_system = res.data.data[0].software_system;
    equipmentDetail.software_version = res.data.data[0].software_version;
    let status=reactive([]);
    status=res.data.data[0].status;
    equipmentDetail.status=''+status;
   /* equipmentDetail.status = res.data.data[0].status;*/



    equipmentDetail.supplierId = res.data.data[0].supplierId;
    equipmentDetail.supplierName = res.data.data[0].supplierName;
    equipmentDetail.thumbnail = res.data.data[0].thumbnail;

    let type=reactive([]);
    type=res.data.data[0].type;
    equipmentDetail.type=''+type;
    // equipmentDetail.type = res.data.data[0].type;

  }
})
const ruleFormRef = ref();
const rules = reactive({
  equipmentName: [{required: true, message: "仿真系统名称不能为空", trigger: "blur"}],
  equipmentNumber: [{required: true, message: "仿真系统编号不能为空", trigger: "blur"}],
});
const onSubmit = () => {
  if (!ruleFormRef) return;
  ruleFormRef.value.validate(async (valid) => {
    if (valid) {

      const res = await userApi.updateSimulationEquipment(equipmentDetail);

      if (res.data != null) {
        if (res.data) {

          ElMessage.info("仿真设备修改成功");
          router.push("/equipment/list");
        } else {
          ElMessage.error("仿真设备修改失败");
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