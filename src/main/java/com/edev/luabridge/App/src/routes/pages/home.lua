luaCoffe.mapping("/home")

local _ = luaCoffe.libs.pages
local names = {"name", "name2", "name3"}
local e = luaCoffe.libs.events

-- Criando uma nova tabela de estados personalizada para 'personState'
luaCoffe.state = luaCoffe.state or {}

-- Usuário pode definir uma tabela personalizada de estados
luaCoffe.state.personState = luaCoffe.state.personState or { showNames = false }

-- Função para exibir as pessoas
local persons = function()
    local result = ""
    if luaCoffe.state.personState.showNames then
        for _, person in ipairs(names) do
            result = result .. "<a>" .. person .. "</a>"
        end
        return result
    else
        return "Cannot show!"
    end
end

-- Evento para alternar o estado showNames
e.addEvent("showNames", function()
    -- Alterna o estado de showNames
    if luaCoffe.state.personState.showNames then
        luaCoffe.state.personState.showNames = false
    else
        luaCoffe.state.personState.showNames = true
    end
end)

-- Função que renderiza o conteúdo da página
local pages = _.div(
        { class = "p-4" },
        _.div(
                { id = "id-123", class = "bg-blue-300 text-white flex gap-10 p-2 items-center justify-center" },
                {
                    _.div({}, "Lista de Pessoas"),
                    persons(),
                    _.button({
                        type = "button",
                        onClick = "trigger('showNames')",  -- Chama o trigger
                        class = "bg-black w-[130px] p-2 rounded-full"
                    }, "Permitir")
                }
        )
)

-- Retorna o código da resposta e o conteúdo da página
return { code = 200, response = pages }
