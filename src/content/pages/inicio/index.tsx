import { useState } from "react";
import ScriptReader from "./components/scriptreader";
import SideBar from "./components/sideabar";
import ScreenCriar from "./components/criarscript";



export default function Inicio() {
    const [opcl, setBoolean] = useState(false)
    const [script, setScriptvalue] = useState("")
    const [scriptName, setScriptName] = useState("")
    const [scriptRoute, setScriptRoute] = useState("")
    const [scriptId, setScriptId] = useState("")
    const [state, setState] = useState(false)
    const OpenCloseScriptEditor = () => {
        if (!opcl) {
            setBoolean(true)
        } else {
            setBoolean(false)
        }


    }
    const openCloseCriarScript = () => {
        setState(!state)
    }
    const setarScript = (scriptD: string, name: string, route: string, id: string) => {
        console.log(scriptD)
        setScriptvalue(scriptD)
        setScriptName(name)
        setScriptRoute(route)
        setScriptId(id)

    }
    return (
        <div className="flex gap-10 items-center">
            <SideBar
                openClose={() => OpenCloseScriptEditor()}
                setarScript={(scriptD: string, name: string, route: string, id: string) => setarScript(scriptD, name, route, id)}
                openCloseCriarScript={() => openCloseCriarScript()}
            />
            {opcl && <ScriptReader script={script} name={scriptName} route={scriptRoute} id={scriptId} />}
            <ScreenCriar state={state} openCloseCriarScript={() => openCloseCriarScript()} />
        </div>
    )
}