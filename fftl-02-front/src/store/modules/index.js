import { createStore } from "vuex";
import createPersistedState from 'vuex-persistedstate';

//이 스토어에 저장한 데이터는 어떤 컴포넌트간에도 공유가 가능합니다.
export default createStore({
    plugins:[
        createPersistedState()
    ],
    state:{
        token:""
    },
})