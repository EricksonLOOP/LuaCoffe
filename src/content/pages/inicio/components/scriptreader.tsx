import axios from "axios"
import { useState } from "react"

export default function ScriptReader({ script, name, route, id }: { script: string, name: string, route: string, id: string }) {
    const [value, setValue] = useState(script)
    const estilizarTexto = (value: string) => {
        const fsor = value.match("for")

    }
    const salvarScript = async () => {
        const req = await axios.patch(`http://localhost:8080/api/lua/save/${id}`,
            {

                script: value,

            }
        )
        alert("salvo!")
    }
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
        <section className="p-3 bg-slate-900 text-white w-[700px] m-auto">
            <header className="text-center flex justify-evenly p-4">
                <p>{name}</p>
                <span className={`${estiloTipo(route)} w-[90px]`}>{route}</span>
                <div>
                    <svg
                        onClick={() => salvarScript()}
                        xmlns="http://www.w3.org/2000/svg"
                        height="24px"
                        viewBox="0 -960 960 960"
                        width="24px"
                        fill="#e8eaed"
                        className="cursor-pointer">
                        <path d="M840-680v480q0 33-23.5 56.5T760-120H200q-33 0-56.5-23.5T120-200v-560q0-33 23.5-56.5T200-840h480l160 160Zm-80 34L646-760H200v560h560v-446ZM480-240q50 0 85-35t35-85q0-50-35-85t-85-35q-50 0-85 35t-35 85q0 50 35 85t85 35ZM240-560h360v-160H240v160Zm-40-86v446-560 114Z" />
                    </svg></div>
            </header>
            <textarea
                name="ScriptEditArea"
                id="scriptArea"
                className="w-[100%] h-[500px] resize-none p-2 text-blue-600 font-bold" value={value} onChange={(e) => setValue(e.target.value)}>

            </textarea>
        </section>
    )
}