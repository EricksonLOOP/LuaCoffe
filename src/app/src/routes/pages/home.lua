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

               _.form(
                       {class = "p-2 flex flex-col gap-4 items-center justify-center", action="http://localhost:8080/api/post/print"},
                       {
                           _.input(
                                   {type="text", placeholder="Aqui esté seu input", value = value},
                                   {}
                           ),
                           _.button(
                                   {type = "submit", class="bg-blue-400 p-2 w-[100px] rounded-md font-bold text-white"},
                                   {
                                       "Enviar"
                                   }
                           )
                       }
               )
)


return { code = 200, response = pages }
