<template>
  <div>
    <el-card >
      <template #header>
        <div class="card-header">

          <span>菜单信息添加</span>
          <el-button size="mini" style="float:right" @click="router.go(-1)">返回</el-button>&nbsp;&nbsp;&nbsp;

        </div>
      </template>

      <el-form :model="menuDetail" :rules="rules" ref="ruleFormRef">
        <p v-if="route.query.edit=='1'">
          <el-form-item label="菜单编号:">
            {{ route.query.menuId }}
          </el-form-item>
        </p>
        <p v-else>
        </p>
        <el-form-item label="菜单名称:">
          <el-input v-model="menuDetail.cname" style="width:300px" placeholder="请输入菜单名称" class="input-with-select"></el-input>

        </el-form-item>
        <el-form-item label="菜单英文名:">
          <el-input v-model="menuDetail.ename" style="width:300px" placeholder="请输入菜单英文名" class="input-with-select"></el-input>
        </el-form-item>
        <el-form-item label="请求地址:">
          <el-input v-model="menuDetail.path" style="width:300px" placeholder="请输入请求地址" class="input-with-select"></el-input>
        </el-form-item>
        <el-form-item label="菜单路径:">
          <el-input v-model="menuDetail.component" style="width:300px" placeholder="请输入菜单路径" class="input-with-select"></el-input>
        </el-form-item>
        <el-form-item label="菜单图标:">
          <el-input v-model="menuDetail.icon" style="width:300px" placeholder="请输入菜单图标" class="input-with-select"></el-input>
        </el-form-item>
        <el-form-item label="父级菜单:">
          <el-input readonly v-model="menuDetail.pid"  style="width:300px" placeholder="请选择父级菜单" class="input-with-select" ></el-input>
          <el-button type="primary" @click="showDialog()">选择</el-button>
        </el-form-item>
        <el-form-item label="菜单排序:">
          <el-input v-model="menuDetail.sort" style="width:300px" placeholder="请输入菜单排序" class="input-with-select"></el-input>
        </el-form-item>
        <el-button type="primary" @click="onSubmit()">保存</el-button>
        <el-button type="primary" @click="resetForm()">重置</el-button>
      </el-form>
    </el-card>
    <el-dialog
        title="选择父级菜单"
        v-model="dialogVisible"
        width="700px"
        :before-close="handleClose">
      <div style="text-align:center;">
        <el-tree
            :data="treeData"
            :check-strictly="true"
            show-checkbox
            :default-expand-all="true"
            ref="treeRef"
            node-key="id"
            :default-expanded-keys="defaultExpandedKeys"
            :default-checked-keys="defaultCheckedKeys"
            :props="defaultProps">
        </el-tree>
      </div>
      <div slot="footer" class="dialog-footer" style="text-align:center;padding-top: 20px;">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleConfirm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script setup>
import { onBeforeMount, reactive, ref } from 'vue';
import { useRouter, useRoute } from 'vue-router'
import userApi from "../../api/user";
import {ElMessage} from "element-plus";
const route = useRoute();
const router = useRouter();
const menuDetail = reactive({
  menuId:'',cname:'',ename:'',path:'',component:'',icon:'',pid:'',sort:''
})
const menuInfo=ref([]);
onBeforeMount(async () => {
  menuDetail.menuId        ='';
  menuDetail.cname          ='';
  menuDetail.ename          ='';
  menuDetail.path           ='';
  menuDetail.component      ='';
  menuDetail.icon           ='';
  menuDetail.pid            ='';
  menuDetail.sort           ='';

})
const ruleFormRef = ref();
const rules = reactive({
  cname: [{ required: true, message: "菜单名称不能为空", trigger: "blur" }],
  path: [{ required: true, message: "请求地址不能为空", trigger: "blur" }],
  component: [{ required: true, message: "菜单路径不能为空", trigger: "blur" }],
});
const onSubmit = () => {
  if (!ruleFormRef) return;
  ruleFormRef.value.validate(async (valid) => {
    if (valid) {
      const res = await userApi.saveMenu(menuDetail);

      if (res.data!=null) {
        if (res.data) {
          ElMessage.info("新增菜单成功");
          router.push("/menu/list");
        } else {
          ElMessage.error("新增菜单失败");
        }
      } else {
        ElMessage.error("服务器内部错误");
      }
    } else {
      return false;
    }
  });
};
const resetForm = () => {
  if (!ruleFormRef) return;
  ruleFormRef.value.resetFields();
};

/******************树开始********************/
const dialogVisible = ref(false); //是否打开弹框

const treeData = ref([/*{
  id: 1,
  label: '一级 1',
  children: [{
    id: 4,
    label: '二级 1-1',
    children: [{
      id: 9,
      label: '三级 1-1-1'
    }, {
      id: 10,
      label: '三级 1-1-2'
    }]
  }]
}, {
  id: 2,
  label: '一级 2',
  children: [{
    id: 5,
    label: '二级 2-1'
  }, {
    id: 6,
    label: '二级 2-2'
  }]
}, {
  id: 3,
  label: '一级 3',
  children: [{
    id: 7,
    label: '二级 3-1'
  }, {
    id: 8,
    label: '二级 3-2'
  }]
}*/])
const defaultProps = reactive({
  children: 'children',
  label: 'label'
})

const defaultCheckedKeys = ref([]);//默认选中的数组
const defaultExpandedKeys = ref([]);//默认展开的数组数组


const treeRef = ref(null);//选中的项的id的数组


//
const showDialog = async(id) => {
  console.log(id)
  //获取所有菜单列表
  const res = await userApi.getMenuAll({});

  if(res.data!=null){
    treeData.value=res.data.data;
    console.log(treeData.value);
  }
  dialogVisible.value = true;
};

const handleConfirm = () => {
  if (treeRef.value) {
    const checkedKeys = treeRef.value.getCheckedKeys(); // 调用 Element Plus Tree 组件的 getCheckedKeys 方法
    if(checkedKeys.length>1){
      ElMessage.error("只能选择一个父节点");
      return ;
    }
    menuDetail.pid=checkedKeys[0];
    console.log('选中的键:', checkedKeys[0]);
    dialogVisible.value = false;
  }
  if (treeRef.value) {
    const checkedNodes = treeRef.value.getCheckedNodes(); // 调用 Element Plus Tree 组件的 getCheckedNodes 方法
    console.log('选中的node:', checkedNodes);
  }


};
/******************树结束********************/
</script>

<style lang="scss" scoped>

</style>