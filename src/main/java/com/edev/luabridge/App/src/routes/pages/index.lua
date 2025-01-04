luaCoffe.mapping("/")

local _ = luaCoffe.libs.pages;

local page = _.div(
        {class = ""},
        {
            _.header({class="p-2 bg-black"},
                    {
                        _.nav({class="flex items-center justify-center"},{
                            _.img({ src="luacoffe.png", class="w-[300px]"},{})
                        })
                    }),
            _.section({class="flex flex-col items-center justify-center p-4 h-[250px] bg-purple-500 text-white"},{
                _.h1({ class="font-black text-[1.4rem]"},{
                    "Welcome to LuaCoffe!"
                }),
                _.p({class="text-center max-w-[500px]"},{
                    [[Welcome to LuaCoffe, a lightweight scripting tool that combines Lua's simplicity with Java's power.]]
                }),
                _.a({ class="w-[200px] bg-stone-900 p-3 rounded-md font-bold mt-5 text-center", href="https://github.com/EricksonLOOP/LuaCoffe#opposys---luacoffe"
                },{
                    "Start now!"
                })
            }),
            _.section({ class="text-center p-6"},{
                _.h2({ class="font-black text-[1.4rem]"},{
                    "Quick tips!"
                }),
                _.p({class="max-w-[500px] m-auto"},{
                    [[
                    Quick tips! Optimize your LuaCoffe development by keeping your code modular, leveraging reactive states for dynamic UIs,
                    and integrating Lua scripts efficiently with your backend. Stay organized, and streamline both frontend and backend tasks!
                    ]]
                }),
                _.div({class="flex justify-center items-start flex-wrap gap-5 mt-5 text-white"},{
                    _.div({class="p-2 rounded-xl bg-purple-500"},{
                        _.h3({ class="font-black text-[1.1rem]"},{
                            "Backend!"
                        }),
                        _.p({ class="w-[300px]"},{
                            "Is here some tips to improve your project with LuaCoffe in backend"
                        }),
                        _.ol({class=" flex flex-col items-center text-center font-bold mt-2 gap-3"},{
                            _.li({},{
                                "1. Dynamic State Binding with HTML ",
                                _.p({class="font-normal text-justify hidden md:block md:max-w-[300px]"},{
                                    [[
                                    LuaCoffe can be used to create modular
                                    scripts that perform recurring tasks on the backend, such as data validation,
                                    transformations, and calculations.
                                    ]]
                                })
                            }),
                            _.li({},{
                                "2. Simple UI Event Handling ",
                                _.p({class="font-normal text-justify hidden md:block md:max-w-[300px]"},{
                                    [[
                                    Use Lua scripts to access and manipulate data directly from the database,
                                    with Java calls to the scripts.
                                    ]]
                                })
                            }),
                            _.li({},{
                                "3. Conditional Rendering and Direct Loops",
                                _.p({class="font-normal text-justify hidden md:block md:max-w-[300px]"},{
                                    [[
                                    Lua is great for handling asynchronous events like API calls or background processing.
                                    ]]
                                })
                            })
                        })
                    }),
                    _.div({class="p-2 rounded-xl bg-purple-500 mt-2"},{
                        _.h3({ class="font-black text-[1.1rem]"},{
                            "Frontend!"
                        }),
                        _.p({ class="w-[300px]"},{
                            "Is here some tips to improve your project with LuaCoffe in Frontend"
                        }),
                        _.ol({class=" flex flex-col items-center text-center font-bold mt-2 gap-3"},{
                            _.li({},{
                                "1. Modularize Lua Scripts for Repetitive Tasks ",
                                _.p({class="font-normal text-justify hidden md:block md:max-w-[300px]"},{
                                    [[
                                   Use LuaCoffe's reactivity to link variables and tables directly to HTML.
                                   This creates interfaces that automatically update when the state in Lua changes.
                                    ]]
                                })
                            }),
                            _.li({},{
                                "2. Direct Integration with the Database ",
                                _.p({class="font-normal text-justify hidden md:block md:max-w-[300px]"},{
                                    [[
                                  LuaCoffe makes it easy to link frontend events, such as clicks and inputs, directly to functions in the Lua script,
                                  without the complexity of front-end frameworks.
                                    ]]
                                })
                            }),
                            _.li({},{
                                "3. Event Flow Control and Asynchronous Tasks ",
                                _.p({class="font-normal text-justify hidden md:block md:max-w-[300px]"},{
                                    [[
                                  LuaCoffe allows you to write loops and conditions directly within HTML.
                                  This is ideal for rendering lists or sections of the page based on the state of the Lua script.
                                   ]]
                                })
                            })
                        })
                    }),
                })
            }),
            _.section({class="p-4 min-h-[400px] flex flex-col gap-10 flex-wrap items-center justify-center"},{
                _.h3({ class="font-black text-[1.4rem]"},{
                    "Are you ready?"
                }),
                _.p({ class="max-w-[600px] text-center"},{
                    [[Are you ready? It's time to dive into LuaCoffe and unlock its full potential.
                     Whether you're crafting powerful backend logic or building responsive, dynamic frontends,
                     LuaCoffe equips you with the tools to create efficient and seamless applications. Get started and transform your development process today!]]
                }),
                _.button({ class="w-[200px] bg-purple-900 text-white p-3 rounded-md font-bold translate-y-[10px] text-center", href="https://github.com/EricksonLOOP/LuaCoffe#opposys---luacoffe"
                },{
                    "Start coding!"
                })

            }),
            _.footer({class="bg-black text-white p-4 font-black text-center bottom-0 w-[100%]"},{
                "Developed by: " .. _.a({ href="https://github.com/EricksonLOOP", class="text-purple-600"},{"@Erickson_diias"})
            })
        })
return {code = 200, response = page}