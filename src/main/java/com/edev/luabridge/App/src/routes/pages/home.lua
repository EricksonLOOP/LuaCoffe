luaCoffe.mapping("/home")

local _ = luaCoffe.libs.pages
local names = {"name", "name2", "name3"}
local e = luaCoffe.libs.events


luaCoffe.state = luaCoffe.state or {}


luaCoffe.state.personState = luaCoffe.state.personState or { state = false }

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


e.addEvent("toggleShowNames", function()
    print("Before toggle: " .. tostring(luaCoffe.state.personState.state))
    luaCoffe.state.personState.state = not luaCoffe.state.personState.state
    print("After toggle: " .. tostring(luaCoffe.state.personState.state))
end)


local pages = _.div(
        { class = "p-4" },
        _.div(
                { id = "id-123", class = "bg-blue-300 text-white flex gap-10 p-2 items-center justify-center" },
                {
                    _.div({}, "Lista de Pessoas"),
                    persons(),
                    _.button({
                        type = "button",
                        onClick = "trigger('toggleShowNames')",
                        class = "bg-black w-[130px] p-2 rounded-full"
                    }, "Alternar Exibição")
                }
        )
)


return { code = 200, response = pages }
