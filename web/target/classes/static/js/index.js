window.onload = function (){

    //vue sfc loader配置
    const options = {
        moduleCache: {
            vue: Vue
        },
        async getFile(url) {
            const res = await fetch(url);
            if ( !res.ok )
                throw Object.assign(new Error(url+' '+res.statusText), { res });
            return await res.text();
        },
        addStyle(textContent) {
            const style = Object.assign(document.createElement('style'), { textContent });
            const ref = document.head.getElementsByTagName('style')[0] || null;
            document.head.insertBefore(style, ref);
        }
    }

    const { loadModule } = window['vue3-sfc-loader'];

    //应用配置
    const App = {
        data(){
            return{
                login: false,
                loginFormVisible:false,
                signinFormVisible:false,
                form1:{
                    nickName:'',
                    password:''
                },
                form2:{
                    avatar:'http://scap.moeneko.top/face.gif',
                    nickName:'',
                    password:'',
                    password2:'',
                    gender:'男'
                },
                upload:{
                    token:''
                }
            }
        },
        computed:{
            nickName(){
                return this.nickName = this.$store.getters.getNickName
            },
            avatar(){
                return this.avatar = this.$store.getters.getAvatar
            }
        },
        created(){
            this.autoLogin()

        },
        components: {
            'study': Vue.defineAsyncComponent( () => loadModule('/static/study.vue', options) ),
            'communicate': Vue.defineAsyncComponent( () => loadModule('/static/communicate.vue', options) )
        },
        methods:{
            handleCommand(command) {
                if (command === '/logout'){
                    axios({
                        method:'get',
                        url:'/logout'
                    }).then(res=>{
                        var res2 = res.data
                        if (res2.code === 200){
                            this.$store.commit('setAvatar','')
                            this.$store.commit('setNickName','')
                            this.$store.commit('setSacpId','')
                            Cookies.remove("nickName",{ path: '/' })
                            Cookies.remove("avatar",{ path: '/' })
                            Cookies.remove("sacpId",{ path: '/' })
                            this.form1.nickName = ''
                            this.form1.password = ''
                            this.login = false
                            this.$router.go(-history.length)
                            this.$router.replace('/')
                        }
                    })
                    this.$message({
                        showClose: false,
                        message: '已退出登录!',
                        type: "success",
                        duration:3000
                    })
                } else if (command === '/mySpace'){
                    // this.$router.push({path: command})
                    const newPage = this.$router.resolve({path: command})
                    window.open(newPage.href,'_blank')
                }else if (command === '/home'){
                    this.$router.push({path: '/'})
                } else {
                    this.$router.push({path: command})
                }
            },
            beforeAvatarUpload(file) {
                const isJPG = file.type === 'image/jpeg';
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isJPG) {
                    this.$message.error('上传头像图片只能是 JPG 格式!');
                    return false
                }
                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 2MB!');
                    return false
                }
                return true
            },
            uploadRequest(request){
                axios.post("/upload/avatar").then(res=>{
                    console.log(res.data)
                    const putExtra = {}
                    const config = {}
                    const observable = qiniu.upload(request.file, new Date().format("yyyyMMddHHmmss")+request.file.name, res.data, putExtra, config)
                    // const subscription = observable.subscribe(observer) // 上传开始
                    // console.log(subscription)
                    const subscription = observable.subscribe(next=>{
                            console.log("正在上传......")
                        },
                        error=>{
                            this.form2.avatar = ''
                            console.log(error)
                        },
                        complete=>{
                            let hash = complete.hash;
                            let key = complete.key;
                            this.form2.avatar = "http://sacp.moeneko.top/" + key;
                            console.log(this.form2.avatar)
                        })
                    return true
                }).catch(e=>{
                    this.$message.error('上传头像失败!');
                    return false
                })
            },
            handleAvatarSuccess(res, file) {
                this.$message.success('上传头像成功!');
            },
            autoLogin(){
                if (Cookies.get("nickName")){
                    console.log(Cookies.get("nickName"))
                    var that = this
                    axios({
                        method:'post',
                        url:'/login',
                        data:{
                            nickName:Cookies.get("nickName")
                        }
                    }).then(res=>{
                        var res2 = res.data
                        if (res2.code === 200){
                            this.$store.commit('setAvatar',res2.result.avatar)
                            this.$store.commit('setNickName',res2.result.nickName)
                            this.$store.commit('setSacpId',res2.result.sacpId)
                            Cookies.remove("nickName",{ path: '/' })
                            Cookies.remove("avatar",{ path: '/' })
                            Cookies.remove("sacpId",{ path: '/' })
                            Cookies.set("nickName",res2.result.nickName,{ expires: 7 })
                            Cookies.set("avatar",res2.result.avatar,{ expires: 7 })
                            Cookies.set("sacpId",res2.result.sacpId,{ expires: 7 })
                            this.login = true
                            this.loginFormVisible = false
                        }
                    })
                }
            },
            loginIn(){
                var that = this
                axios({
                    method:'post',
                    url:'/login',
                    data:{
                        nickName:this.form1.nickName,
                        password:this.form1.password
                    }
                }).then(res=>{
                    var res2 = res.data
                    console.log(res2)
                    if (res2.code === 200){
                        this.$store.commit('setAvatar',res2.result.avatar)
                        this.$store.commit('setNickName',res2.result.nickName)
                        this.$store.commit('setSacpId',res2.result.sacpId)
                        Cookies.remove("nickName",{ path: '/' })
                        Cookies.remove("avatar",{ path: '/' })
                        Cookies.remove("sacpId",{ path: '/' })
                        Cookies.set("nickName",res2.result.nickName,{ expires: 7 })
                        Cookies.set("avatar",res2.result.avatar,{ expires: 7 })
                        Cookies.set("sacpId",res2.result.sacpId,{ expires: 7 })
                        this.login = true
                        this.loginFormVisible = false
                    }else {
                        this.$message.error('登录失败!');
                    }
                })
            },
            signUp(){
                if (!this.form2.avatar||this.form2.avatar === ''){
                    this.$message({
                        showClose: true,
                        message: '头像上传失败或没有选择头像',
                        type: 'warning'
                    })
                    return
                }
                if (!this.form2.nickName||this.form2.nickName === ''){
                    this.$message({
                        showClose: true,
                        message: '昵称不能为空',
                        type: 'warning'
                    })
                    return;
                }
                if (!this.form2.password||this.form2.password === ''){
                    this.$message({
                        showClose: true,
                        message: '密码不能为空',
                        type: 'warning'
                    })
                    return;
                }
                if (this.form2.password!=this.form2.password2){
                    this.$message({
                        showClose: true,
                        message: '两次输入的密码不一致',
                        type: 'warning'
                    })
                    return;
                }

                var that = this
                axios({
                    method:'post',
                    url:'/signup',
                    data:this.form2
                }).then(res=>{
                    var res2 = res.data
                    console.log(res2)
                    if (res2.code === 200){
                        this.$message.success('注册成功!');
                        this.signinFormVisible = false;
                    }else {
                        this.$message.error(res2.message);
                    }
                })

            }
        }
    }

    //路由配置
    const routes = [
        { path: '', component:()=> loadModule('static/study.vue', options)},
        { path: '/study', component:()=> loadModule('/static/study.vue', options),
            children: [
                {
                    path: '',
                    component: ()=> loadModule('/static/components/courseList.vue',options)
                },
                {
                    path: 'myCourse/:classifyId',
                    component: ()=> loadModule('/static/components/courseList.vue',options)
                }
            ]
        },
        { path: '/communicate', component:()=> loadModule('/static/communicate.vue', options) },
        { path: '/courseDetail/:courseId', component:()=> loadModule('/static/components/courseDetail.vue', options) },
        { path: '/courseBlock/:blockId', component:()=> loadModule('/static/components/courseBlock.vue', options) },
        { path: '/postDetail', component:()=> loadModule('/static/components/postDetail.vue', options) },
        { path: '/mySpace', component:()=> loadModule('/static/components/mySpace.vue', options),
            children: [
                {
                    path: '',
                    component: ()=> loadModule('/static/components/myCourse.vue',options)
                },
                {
                    path: 'myCourse',
                    component: ()=> loadModule('/static/components/myCourse.vue',options)
                },
                {
                    path: 'myCommunication',
                    component: ()=> loadModule('/static/components/myCommunication.vue',options)
                },
                {
                    path: 'myInfo',
                    component: ()=> loadModule('/static/components/myInfo.vue',options)
                }
            ]
        },
        { path: '/404', component:()=> loadModule('static/components/page404.vue', options)},
        { path: '/:pathMatch(.*)*', redirect:"/404"}
    ]
    const router = VueRouter.createRouter({
        history: VueRouter.createWebHashHistory(),
        routes:routes,
    })

    //vuex配置
    const store = Vuex.createStore({
        state() {
            return{
                avatar: '',
                classfifyId: '',
                createTime: '',
                gender: '',
                id: '',
                nickName: '',
                sacpId: '',
                status: ''
            }
        },
        getters:{
            getAvatar(state){
                return state.avatar
            },
            getNickName(state){
                return state.nickName
            },
            getSacpId(state){
                return state.sacpId
            }
        },
        mutations: {
            setAvatar(state,data){
                state.avatar = data
            },
            setNickName(state,data){
                state.nickName = data
            },
            setSacpId(state,data){
                state.sacpId = data
            }
        }
    })

    //挂载应用
    const app = Vue.createApp(App);
    app.use(router);
    app.use(store)
    app.use(ElementPlus);
    app.mount('#app');



    //日期格式化
    Date.prototype.format = function(fmt) {
        var o = {
            "M+" : this.getMonth()+1,                 //月份
            "d+" : this.getDate(),                    //日
            "H+" : this.getHours(),                   //小时
            "m+" : this.getMinutes(),                 //分
            "s+" : this.getSeconds(),                 //秒
            "q+" : Math.floor((this.getMonth()+3)/3), //季度
            "S"  : this.getMilliseconds()             //毫秒
        };
        if(/(y+)/.test(fmt)) {
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        }
        for(var k in o) {
            if(new RegExp("("+ k +")").test(fmt)){
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
            }
        }
        return fmt;
    }
}