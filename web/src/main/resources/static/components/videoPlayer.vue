<template>
  <div style="width: 600px;height: 400px">
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