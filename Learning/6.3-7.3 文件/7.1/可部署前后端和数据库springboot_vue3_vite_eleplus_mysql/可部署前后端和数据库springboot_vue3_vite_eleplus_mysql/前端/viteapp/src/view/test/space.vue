<template>
  <el-space direction="vertical">
    <el-card v-for="i in 5" :key="i" class="box-card" style="width: 250px">
      <template #header>
        <div class="card-header">
          <span>Card name</span>
          <el-button class="button" text>Operation button</el-button>
        </div>
      </template>
      <div v-for="o in 10" :key="o" class="text item">
        {{ 'List item ' + o }}
      </div>
    </el-card>
  </el-space>
</template>