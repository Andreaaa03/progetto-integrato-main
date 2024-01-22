/** @type {import('tailwindcss').Config} */
module.exports = {
    content: [
        "./src/**/*.{html,ts}",
    ],
    theme: {
        extend: {
            colors: {
                primary: "#185073",
                secondary: "#1671AA",
                tertiary: "#BAD5E6"
            }
        },
    },
    plugins: [],
}