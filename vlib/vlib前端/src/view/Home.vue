<template>
  <div>
    <el-container class="home-container">
      <!-- header -->
      <el-header>
        <el-row>
          <el-col :span="4">
            <p class="system-name">实训课堂管理系统</p>
          </el-col>
          <el-col :offset="12" :span="8" style="min-width: 150px">
            <el-dropdown style="float: right; margin: 20px 10px">
                            <span class="el-dropdown-link" style="color: #fff; cursor: pointer">
                                {{userName}} &nbsp;&nbsp; <el-icon class="el-icon--right">
                                    <arrow-down />
                                </el-icon>
                            </span>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item @click.native="logout">退出系统</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
            <el-avatar shape="square" :src="avatar" style="margin: 10px; float: right"></el-avatar>
          </el-col>
        </el-row>
      </el-header>

      <el-container style="overflow: auto">
        <!-- 菜单 -->
        <el-aside>
          <div class="toggle-button" @click="isCollapse = !isCollapse">
            <el-icon :size="20">
              <Expand v-if="isCollapse"/>
              <Fold v-if="!isCollapse"/>
            </el-icon>
          </div>
          <el-menu router :default-active="activePath" class="el-menu-vertical-demo" :collapse="isCollapse">
            <!--            <el-menu-item index="/index" @click="saveActiveNav('/index')">
                            <el-icon>
                                <house />
                            </el-icon>
                            <span>首页</span>
                        </el-menu-item>
                        <el-sub-menu index="1">
                            <template #title>
                                <el-icon>
                                    <Setting />
                                </el-icon>
                                <span>系统设置</span>
                            </template>
                            <el-menu-item index="2-1">权限管理</el-menu-item>
                        </el-sub-menu>
                        <el-menu-item index="/user/list" @click="saveActiveNav('/user/list')">
                            <el-icon>
                                <user />
                            </el-icon>
                            <span>用户管理</span>
                        </el-menu-item>
                        <el-menu-item index="/role/list" @click="saveActiveNav('/role/list')">
                        <el-icon>
                          <user />
                        </el-icon>
                        <span>角色管理</span>
                      </el-menu-item>
                      <el-menu-item index="/menu/list" @click="saveActiveNav('/menu/list')">
                        <el-icon>
                          <user />
                        </el-icon>
                        <span>菜单管理</span>
                      </el-menu-item>-->
            <div v-for="item in treeData">
              <el-sub-menu v-if="item.children" :index="item.id">
                <template #title>
                  <el-icon>
                    <component :is="item.elIcon"/>
                  </el-icon>
                  <span>{{ item.label }}</span>
                </template>
                <div v-for="childrenItem in item.children">
                  <el-sub-menu v-if="childrenItem.children" :index="childrenItem.id">
                    <template #title>
                      <el-icon>
                        <component :is="childrenItem.elIcon"/>
                      </el-icon>
                      <span>{{ childrenItem.label }}</span>
                    </template>
                    <el-menu-item v-for="childrenItem1 in childrenItem.children" :index="childrenItem1.id"
                                  @click="saveActiveNav(childrenItem1.id)">{{ childrenItem1.label }}
                    </el-menu-item>
                  </el-sub-menu>
                  <el-menu-item v-else :index="childrenItem.id" @click="saveActiveNav(childrenItem.id)">
                    <template #title>
                      <el-icon>
                        <component :is="childrenItem.elIcon"/>
                      </el-icon>
                      <span>{{ childrenItem.label }}</span>
                    </template>
                  </el-menu-item>
                </div>
              </el-sub-menu>
              <el-menu-item v-else :index="item.id" @click="saveActiveNav(item.id)">
                <template #title>
                  <el-icon>
                    <component :is="item.elIcon"/>
                  </el-icon>
                  <span>{{ item.label }}</span>
                </template>
              </el-menu-item>
            </div>
          </el-menu>
        </el-aside>
        <el-container>
          <el-main>
            <!-- 面包屑 -->
            <!-- <Breadcrumb /> -->
            <!-- 主要内容 -->
            <router-view></router-view>
          </el-main>
          <el-footer>Copyright © 2024</el-footer>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>
<script setup>
import {onBeforeMount, ref} from 'vue';
import avatar from "../assets/img/avator.jpg"
import {useRoute, useRouter} from 'vue-router'
import userApi from "../api/user";

const router = useRouter();
const route = useRoute();
let userId=ref();
let userName=ref();
// 挂载 DOM 之前
onBeforeMount(async () => {
  if (null != route.query.userId) {
    userId.value = route.query.userId;
    userName.value=route.query.userName;
    sessionStorage.setItem("userName",route.query.userName);
    sessionStorage.setItem("userId",route.query.userId);
   }
  if(null==route.query.userId){
    userId.value=sessionStorage.getItem("userId");
    userName.value=sessionStorage.getItem("userName");
  }
  const res = await userApi.getUserMenu({userId: userId.value});

  treeData.value = res.data.data;
  activePath.value = sessionStorage.getItem("activePath")
      ? sessionStorage.getItem("activePath")
      : "/index"
})
let isCollapse = ref(false);
let activePath = ref("");

// const defaultOpeneds = ref(['info', 'reserve', 'user'])

const treeData = ref([
 /* {
    id: '/index',
    label: '首页',
    elIcon: 'house',
  }, {
    id: '1',
    label: '系统设置',
    elIcon: 'house',
    children: [{
      id: '/user/list',
      label: '权限管理',
      elIcon: 'Search',
      children: [{
        id: '/role/list',
        label: '角色管理',
        elIcon: 'Search',
      }]
    }, {
      id: '/user/list',
      label: '权限管理',
      elIcon: 'house',
    }]
  }, {
    id: '/user/list',
    label: '用户管理',
    elIcon: 'house',
  }, {
    id: '/role/list',
    label: '角色管理',
    elIcon: 'Search',
  }, {
    id: '/menu/list',
    label: '菜单管理',
    elIcon: 'house',
  }*/
])

//treeData.value=res.data.data;
// 保存链接的激活状态
const saveActiveNav = (path) => {
  sessionStorage.setItem("activePath", path);
  activePath.value = path;
}
const logout = () => {
  // 清除缓存
  sessionStorage.clear();
  router.push("/login");
}
</script>

<style scoped>
.home-container {
  position: absolute;
  height: 100%;
  top: 0px;
  left: 0px;
  width: 100%;
  background: #f2f3f5;
}

.el-header {
  background: #2661ef;
  padding: 0 10px;
  overflow: hidden;
}

.system-name {
  color: #fff;
  font-size: 18px;
}

.el-aside {
  background: white;
  width: auto !important;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}

.el-footer {
  color: #cccccc;
  text-align: center;
  line-height: 60px;
}

.el-footer:hover {
  color: #2661ef;
}

.toggle-button {
  background-color: #d9e0e7;
  font-size: 18px;
  line-height: 24px;
  color: #fff;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer;
  color: black;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}

.el-menu-item.is-active {
  color: #fff !important;
  font-size: 15px;
  font-weight: bold;
  background-color: #2661ef !important;
  border-radius: 2px;
  height: 50px;
  line-height: 50px;
  box-sizing: border-box;
  margin: 2px 5px 0px 2px;
}
</style>