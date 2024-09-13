/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{js,jsx,ts,tsx}",],
  theme: {
    extend: {
      rotate: {
        '30': '30deg', // Add a custom rotation class
      },
    },
  },
  plugins: [],
}

