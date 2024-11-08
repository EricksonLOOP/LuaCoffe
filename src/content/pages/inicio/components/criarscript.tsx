import axios from "axios"
import { useState } from "react"
import { motion } from "framer-motion"

export default function ScreenCriar({ state, openCloseCriarScript }: { state: boolean, openCloseCriarScript: Function }) {
    const [name, setName] = useState("")
    const [route, setRoute] = useState("")

    const criarScript = async (event: React.FormEvent) => {
        const req = await axios.post("http://localhost:8080/api/lua/create", {
            name: name,
            route: route,
            script: ""
        })
        console.log(req)
    }

    return (
        <motion.form className="absolute top-5 left-80 bg-slate-950 p-4 flex flex-col gap-5 rounded-xl" onSubmit={criarScript}
            animate={state ? {} : { display: "none" }}
        >
            <header className="flex items-center justify-center gap-10">
                <h3 className="text-white text-[1.2rem] text-center font-bold">Criando Rota</h3>

                <svg
                    xmlns="http://www.w3.org/2000/svg"
                    height="24px"
                    viewBox="0 -960 960 960"
                    width="24px"
                    fill="#e8eaed"
                    className="translate-x-16 cursor-pointer"
                    onClick={() => openCloseCriarScript()}
                >
                    <path d="m336-280 144-144 144 144 56-56-144-144 144-144-56-56-144 144-144-144-56 56 144 144-144 144 56 56ZM480-80q-83 0-156-31.5T197-197q-54-54-85.5-127T80-480q0-83 31.5-156T197-763q54-54 127-85.5T480-880q83 0 156 31.5T763-763q54 54 85.5 127T880-480q0 83-31.5 156T763-197q-54 54-127 85.5T480-80Zm0-80q134 0 227-93t93-227q0-134-93-227t-227-93q-134 0-227 93t-93 227q0 134 93 227t227 93Zm0-320Z" />
                </svg>
            </header>
            <div className="flex gap-5 items-center">
                <label htmlFor="name" className="flex flex-col text-white">
                    Nome
                    <input type="text" placeholder="Script Name" id="name" className="p-1 rounded-xl text-black text-[1.2rem]" value={name} onChange={(e) => setName(e.target.value)} />
                </label>
                <label htmlFor="route" className="text-white">
                    RouteType
                    <select name="route" id="route" className="flex w-[100px] text-black text-center font-extrabold text-[1.2rem]" value={route} onChange={(e) => setRoute(e.target.value)}>
                        <option value="GET" className=" text-white bg-green-700 font-extrabold">GET</option>
                        <option value="POST" className=" text-white bg-blue-700 font-extrabold">POST</option>
                        <option value="PUT" className=" text-white bg-purple-700 font-extrabold">PUT</option>
                        <option value="DELETE" className=" text-white bg-red-700 font-extrabold">DELETE</option>
                    </select>
                </label>
            </div>
            <button type="submit" className="w-[100%] text-white bg-blue-800 p-2 font-bold rounded-full text-[1.2rem]">Criar</button>
        </motion.form>
    )
}
