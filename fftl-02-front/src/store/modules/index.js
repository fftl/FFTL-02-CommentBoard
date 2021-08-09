import { createStore } from "vuex";
import createPersistedState from 'vuex-persistedstate';

//이 스토어에 저장한 데이터는 어떤 컴포넌트간에도 공유가 가능합니다.
export default createStore({
    plugins:[
        createPersistedState()
    ],
    state:{
        token:"",
        nickname:"",
        uid:"",
        loginCheck:false,
    },
    mutations: {
        //setToken 을 이용해서 state의 값을 할당 해줍니다.
        setToken(state, value) {
            state.token = value;
        },
        setNickname(state, value){
            state.nickname = value;
        },
        setUid(state, value){
            state.uid = value;
        },
        setLoginCheck(state, value){
            state.loginCheck = value;
        },
        //로그아웃 할 때 사용합니다.
        resetState(state){ 
            state.token="";
            state.nickname="";
            state.uid="";
            state.loginCheck=false;
        }
    }
})