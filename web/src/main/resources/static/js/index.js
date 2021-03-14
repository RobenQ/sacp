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
                login: false
            }
        },
        components: {
            'study': Vue.defineAsyncComponent( () => loadModule('/static/study.vue', options) ),
            'communicate': Vue.defineAsyncComponent( () => loadModule('/static/communicate.vue', options) )
        },
        methods:{
            handleCommand(command) {
                if (command === '/logout'){
                    this.$message({
                        showClose: false,
                        message: '模拟退出登录：退出登录成功!',
                        type: "success",
                        duration:3000
                    })
                } else if (command === '/mySpace'){
                    this.$router.push({path: command})
                }else if (command === '/home'){
                    this.$router.push({path: '/'})
                } else {
                    this.$router.push({path: command})
                }
            }
        }
    }

    //路由配置
    const routes = [
        { path: '', component:()=> loadModule('static/study.vue', options)},
        { path: '/study', component:()=> loadModule('/static/study.vue', options) },
        { path: '/communicate', component:()=> loadModule('/static/communicate.vue', options) },
        { path: '/courseDetail', component:()=> loadModule('/static/components/courseDetail.vue', options) },
        { path: '/courseBlock', component:()=> loadModule('/static/components/courseBlock.vue', options) },
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

    //挂载应用
    const app = Vue.createApp(App);
    app.use(router);
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