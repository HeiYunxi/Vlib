import { createRouter, createWebHashHistory } from 'vue-router'
const routes = [
    {
        path: '/',
        redirect: '/login'
    },
    {
        path: '/login',
        name: 'login',
        meta: {
            title: '登录'
        },
        component: () => import('../view/login.vue')
    },
    {
        path: '/register',
        meta: {
            title: '用户注册'
        },
        component: () => import('../view/register.vue'),
    },
    {
        path: '/home',
        name: '主页',
        meta: {
            title: '主页'
        },
        component: () => import('../view/Home.vue'),
        redirect: '/index',
        children: [
            {
                path: '/index',
                meta: {
                    title: '首页'
                },
                component: () => import('../view/data_view/Index.vue')
            },
            {
                path: '/user/list',
                meta: {
                    title: '用户管理'
                },
                component: () => import('../view/user/Index.vue'),
            },
            {
                path: '/user/detail',
                meta: {
                    title: '用户详情'
                },
                component: () => import('../view/user/Detail.vue'),
            },{
                path: '/user/save',
                meta: {
                    title: '用户新增'
                },
                component: () => import('../view/user/Add.vue'),
            },{
                path: '/role/list',
                meta: {
                    title: '角色管理'
                },
                component: () => import('../view/role/Index.vue'),
            },{
                path: '/role/save',
                meta: {
                    title: '角色新增'
                },
                component: () => import('../view/role/Add.vue'),
            },{
                path: '/role/detail',
                meta: {
                    title: '角色明细'
                },
                component: () => import('../view/role/Detail.vue'),
            },{
                path: '/menu/list',
                meta: {
                    title: '菜单管理'
                },
                component: () => import('../view/menu/Index.vue'),
            },{
                path: '/menu/save',
                meta: {
                    title: '菜单新增'
                },
                component: () => import('../view/menu/Add.vue'),
            },{
                path: '/menu/detail',
                meta: {
                    title: '菜单明细'
                },
                component: () => import('../view/menu/Detail.vue'),
            },{
                path: '/equipment/list',
                meta: {
                    title: '虚拟设备管理'
                },
                component: () => import('../view/equipment/Index.vue'),
            },{
                path: '/equipment/save',
                meta: {
                    title: '虚拟设备新增'
                },
                component: () => import('../view/equipment/Add.vue'),
            },{
                path: '/equipment/detail',
                meta: {
                    title: '虚拟设备明细'
                },
                component: () => import('../view/equipment/Detail.vue'),
            },{
                path: '/equipment/booking',
                meta: {
                    title: '虚拟设备管理'
                },
                component: () => import('../view/equipment/booking.vue'),
            },{
                path: '/equipment/myBooking',
                meta: {
                    title: '我的预定'
                },
                component: () => import('../view/equipment/myBooking.vue'),
            },{
                path: '/lab/list',
                meta: {
                    title: '实验室'
                },
                component: () => import('../view/lab/Index.vue'),
            },{
                path: '/lab/save',
                meta: {
                    title: '实验室新增'
                },
                component: () => import('../view/lab/Add.vue'),
            },{
                path: '/lab/detail',
                meta: {
                    title: '实验室明细'
                },
                component: () => import('../view/lab/Detail.vue'),
            },
            {
                path: '/supplier/list',
                meta: {
                    title: '供应商'
                },
                component: () => import('../view/supplier/Index.vue'),
            },{
                path: '/supplier/save',
                meta: {
                    title: '供应商新增'
                },
                component: () => import('../view/supplier/Add.vue'),
            },{
                path: '/supplier/detail',
                meta: {
                    title: '供应商明细'
                },
                component: () => import('../view/supplier/Detail.vue'),
            },
            {
                path: '/lab/labList',
                meta: {
                    title: '实验室列表'
                },
                component: () => import('../view/lab/labList.vue'),
            },{
                path: '/lab/apply',
                meta: {
                    title: '实验室申请'
                },
                component: () => import('../view/apply/Apply.vue'),
            },
            {
                path: '/lab/approve',
                meta: {
                    title: '实验室审核'
                },
                component: () => import('../view/apply/Approve.vue'),
            },{
                path: '/data/view',
                meta: {
                    title: '数据可视化'
                },
                component: () => import('../view/data_view/Index.vue'),
            }
        ]
    },
]
const router = createRouter({
    history: createWebHashHistory(),
    routes
})
// 挂载路由导航守卫：to表示将要访问的路径，from表示从哪里来，next是下一个要做的操作
router.beforeEach((to, from, next) => {
    // 修改页面 title
    if (to.meta.title) {
      document.title = '课堂后台管理系统 - ' + to.meta.title
    }
    // 放行登录页面
    if (to.path === '/login') {
      return next()
    }
    // 获取token
    // const token= sessionStorage.getItem('token')
    // if (!token) {
    //   return next('/login')
    // } else {
    //   next()
    // }
    return next()
  })
  
// 导出路由
export default router