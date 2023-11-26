This is a [Next.js](https://nextjs.org/) project bootstrapped with [`create-next-app`](https://github.com/vercel/next.js/tree/canary/packages/create-next-app).

## Getting Started

First, run the development server:

```bash
npm run dev
# or
yarn dev
```

## Install Tailwind CSS

- [Install Tailwind CSS reference](https://tailwindcss.com/docs/guides/nextjs)

```

npm install -D tailwindcss postcss autoprefixer
npx tailwindcss init -p

```

### Configure Tailwind

#### Changing the Config

```
    module.exports = {
    content: [
        "./pages/**/*.{js,ts,jsx,tsx}",
        "./components/**/*.{js,ts,jsx,tsx}",
    ],
    theme: {
        extend: {},
    },
    plugins: [],
    }
```

#### Changing the Globals.css

```
    @tailwind base;
    @tailwind components;
    @tailwind utilities;
```

Open [http://localhost:3000](http://localhost:3000) with your browser to see the result.

## References

- []()
