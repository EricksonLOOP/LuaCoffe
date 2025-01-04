luaCoffe.mapping("/home")

local _ = luaCoffe.libs.pages
local names = {"name", "name2", "name3"}
local e = luaCoffe.libs.events

-- Criando uma nova tabela de estados personalizada para 'personState'
luaCoffe.state = luaCoffe.state or {}

-- Usuário pode definir uma tabela personalizada de estados
luaCoffe.state.personState = luaCoffe.state.personState or { state = false }

-- Função para exibir as pessoas
local persons = function()
    local result = ""
    if luaCoffe.state.personState.state then
        for _, person in ipairs(names) do
            result = result .. "<a>" .. person .. "</a>"
        end
        return result
    else
        return "Cannot show!"
    end
end

-- Evento para alternar o estado showNames
e.addEvent("toggleShowNames", function()
    -- Alterar o estado de 'showNames'
    print("Before toggle: " .. tostring(luaCoffe.state.personState.state))  -- Debugging line
    luaCoffe.state.personState.state = not luaCoffe.state.personState.state  -- Alterna entre true/false
    print("After toggle: " .. tostring(luaCoffe.state.personState.state))  -- Debugging line
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
                        onClick = "trigger('toggleShowNames')",  -- Chama o trigger
                        class = "bg-black w-[130px] p-2 rounded-full"
                    }, "Alternar Exibição")
                }
        )
)

-- Retorna o código da resposta e o conteúdo da página
return { code = 200, response = pages }
