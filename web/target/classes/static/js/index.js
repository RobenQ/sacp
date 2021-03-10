window.onload = function (){
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
        }
    }
    const routes = [
        { path: '/', component:()=> loadModule('static/study.vue', options)},
        { path: '/study',name:"study", component:()=> loadModule('/static/study.vue', options) },
        { path: '/communicate',name:"communicate", component:()=> loadModule('/static/communicate.vue', options) }
    ]
    const router = VueRouter.createRouter({
        history: VueRouter.createWebHashHistory(),
        routes:routes,
    })
    const app = Vue.createApp(App);
    app.use(router);
    app.use(ElementPlus);
    app.mount('#app');
}