<template>
  <div style="width: 600px;height: 400px">
<!--    视频播放器组件，视频封面和视频地址通过父组件传入，在props域中-->
    <div id="video" style="width: 100%;height: 100%;"></div>
  </div>
</template>

<script>
export default {
  name: "videoPlayer",
  props:['face','video'],
  data(){
    return{

    }
  },
  computed:{
    poster(){
      return this.face
    },
    videoUrl(){
      return this.video
    }
  },
  created(){
    this.$nextTick(()=>this.init())
  },
  methods:{
    init(){
      var videoObject = {
        container: '#video',
        poster:this.face,
        video:this.videoUrl
      }
      var player = new ckplayer(videoObject)
    }
  }
}
</script>

<style scoped>
</style>