import axios from "axios"
import { useEffect, useState } from "react"
import { motion } from "framer-motion"
interface Script {
    name: string;
    id: string;
    script: string;
    route: string;
}
export default function SideBar({ openClose, setarScript, openCloseCriarScript }: { openClose: Function, setarScript: Function, openCloseCriarScript: Function }) {
    const [scripts, setScripts] = useState(Array<Script>);
    useEffect(() => {
        const pegarScripts = async () => {
            const req = await axios.get("http://localhost:8080/api/lua/scripts/list")
            console.log(req.data)
            setScripts(req.data)
        }
        pegarScripts();
    }, [])
    const estiloTipo = (tipo: string) => {
        switch (tipo) {
            case "POST":
                return "bg-blue-500 text-white"
                break;
            case "DELETE":
                return "bg-red-500 text-white"
                break;
            case "GET":
                return "bg-green-500 text-white"
                break;

            default:
                return "bg-gray-500 text-white"
                break;
        }
    }
    return (
        <motion.aside className="p-3 bg-slate-900 w-[250px] h-[100vh] text-white shadow-2xl">
            <span className="flex p-4 justify-end">

                <svg xmlns="http://www.w3.org/2000/svg"
                    height="24px" viewBox="0 -960 960 960"
                    width="24px"
                    fill="#e8eaed"
                    onClick={() => openCloseCriarScript()}
                    className="cursor-pointer"
                >
                    <path d="M440-280h80v-160h160v-80H520v-160h-80v160H280v80h160v160Zm40 200q-83 0-156-31.5T197-197q-54-54-85.5-127T80-480q0-83 31.5-156T197-763q54-54 127-85.5T480-880q83 0 156 31.5T763-763q54 54 85.5 127T880-480q0 83-31.5 156T763-197q-54 54-127 85.5T480-80Zm0-80q134 0 227-93t93-227q0-134-93-227t-227-93q-134 0-227 93t-93 227q0 134 93 227t227 93Zm0-320Z" />
                </svg>
            </span>
            <ul className="w-[100%] flex  flex-col gap-2">
                {scripts.map((script: Script) => {
                    return (
                        <li
                            key={script.id}
                            onClick={() => { setarScript(script.script, script.name, script.route, script.id); openClose() }}
                            className="w-[100%] flex justify-between  items-center p-1 border text-center cursor-pointer border-gray-400 rounded-3xl">
                            {script.name} <span className={`${estiloTipo(script.route)} block p-1 w-[90px] text-center rounded-3xl`}>{script.route}</span>
                        </li>
                    )
                })}
            </ul>
        </motion.aside>
    )
}