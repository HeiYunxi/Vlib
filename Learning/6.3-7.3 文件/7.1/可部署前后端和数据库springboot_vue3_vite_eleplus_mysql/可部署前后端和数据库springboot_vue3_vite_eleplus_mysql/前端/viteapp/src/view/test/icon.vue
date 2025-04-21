<template>
  <p>
    with extra class <b>is-loading</b>, your icon is able to rotate 360 deg in 2
    seconds, you can also override this
  </p>
  <el-icon :size="20">
    <Edit />
  </el-icon>
  <el-icon color="#409EFC" class="no-inherit">
    <Share />
  </el-icon>
  <el-icon>
    <Delete />
  </el-icon>
  <el-icon class="is-loading">
    <Loading />
  </el-icon>
  <el-button type="primary">
    <el-icon style="vertical-align: middle">
      <Search />
    </el-icon>
    <span style="vertical-align: middle"> Search </span>
  </el-button>

  <div style="font-size: 20px">
    <!-- 由于SVG图标默认不携带任何属性 -->
    <!-- 你需要直接提供它们 -->
    <Edit style="width: 1em; height: 1em; margin-right: 8px" />
    <Share style="width: 1em; height: 1em; margin-right: 8px" />
    <Delete style="width: 1em; height: 1em; margin-right: 8px" />
    <Search style="width: 1em; height: 1em; margin-right: 8px" />
  </div>
</template>