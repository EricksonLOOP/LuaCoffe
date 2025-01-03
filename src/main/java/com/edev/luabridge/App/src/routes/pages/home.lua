luaCoffe.mapping("/home")

local _ = luaCoffe.libs.pages;
local names = {"name", "name2", "name3"}

local showPersons = false;

local persons = function()
    local result = ""
    if showPersons then
        for _, person in ipairs(names) do
            result = result .. "<a>"..person.."</a>"
        end
        return result
    else
        return "Cannot show!"
    end
end

local pages =
_.div(
        { class = "bg-blue-300" },
        _.div(
                { id = "id-123", class = "text-white flex gap-10 p-2" },
                {
                    _.div({}, "Lista de Pessoas"),
                     persons()
 }
        )
)

return { code = 200, response = pages }
