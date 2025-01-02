luaCoffe.mapping("pages/index")
local alertar = function()
    return "window.alert('clicou')"
end
local page = [[

    <h1 onClick=]]..alertar()..[[ class=]].."abc"..[[>
        Página!
    </h1>
]]
return {code = 200, response = page}