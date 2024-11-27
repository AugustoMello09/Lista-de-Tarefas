/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,ts}",
    "./node_modules/flowbite/**/*.js"
  ],
  theme: {
    extend: {
      fontFamily: {
        'dm': ['DM Sans', 'sans-serif'],
        'Gro' : ['Space Grotesk', 'sans-serif']
      },
      screens: {
        'auto': {'max' : '1024px'},
        'menor': {'max' : '824px'},
        'xr': {'max' : '599px'},
        'xd': {'max' : '409px'},
      }
    },
  },
  plugins: [
    require('flowbite/plugin')
  ],
}

