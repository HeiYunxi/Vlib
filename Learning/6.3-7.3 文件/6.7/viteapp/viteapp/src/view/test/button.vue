<template>
  <el-row class="mb-4">
    <el-button type="primary">按钮</el-button>
    <el-button type="primary" :icon="Edit">按钮</el-button>
    <el-button type="primary" :icon="Edit" />
    <el-button type="primary" disabled>按钮</el-button>
  </el-row>

  <el-row class="mb-4">
    <el-button type="info">按钮</el-button>
    <el-button type="info" :icon="Edit">按钮</el-button>
    <el-button type="info" :icon="Edit" />
    <el-button type="info" disabled>按钮</el-button>
  </el-row>

  <el-row class="mb-4">
    <el-button type="primary" plain>按钮</el-button>
    <el-button type="primary" plain :icon="Edit">按钮</el-button>
    <el-button type="primary" plain :icon="Edit" />
    <el-button type="primary" plain :icon="Edit" disabled />
  </el-row>

  <el-row class="mb-4">
    <el-button type="primary" link>按钮</el-button>
    <el-button type="primary" link :icon="Edit">按钮</el-button>
    <el-button type="primary" link :icon="Edit" />
    <el-button type="primary" link :icon="Edit" disabled />
  </el-row>
</template>

<script lang="ts" setup>
import { Edit } from '@element-plus/icons-vue'
</script>
