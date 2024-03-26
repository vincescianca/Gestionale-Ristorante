import DashboardLayout from "@/layout/dashboard/DashboardLayout.vue";
// GeneralViews
import NotFound from "@/pages/NotFoundPage.vue";

// Admin pages
import Dashboard from "@/pages/Dashboard.vue";
import UserProfile from "@/pages/UserProfile.vue";
import Notifications from "@/pages/Notifications.vue";
import Icons from "@/pages/Icons.vue";
import Maps from "@/pages/Maps.vue";
import Typography from "@/pages/Prodotti.vue";
import TableList from "@/pages/Tavoli.vue";
import Prodotti from "@/pages/Prodotti";
import AggiungiProdotto from "@/pages/AggiungiProdotto";
import ModificaProdotto from "@/pages/ModificaProdotto";
import Categorie from "@/pages/Categorie";
import AggiungiCategoria from "@/pages/AggiungiCategoria";
import ModificaCategoria from "@/pages/ModificaCategoria";
import Menu from "@/pages/Menu";
import AggiungiMenu from "@/pages/AggiungiMenu";
import ModificaMenu from "@/pages/ModificaMenu";
import AggiungiTavolo from "@/pages/AggiungiTavolo";
import AggiungiPrenotazione from "@/pages/AggiungiOrdine";
import Tavoli from "@/pages/Tavoli";
import TavoliAll from "@/pages/TavoliAll";
import Prodotto from "@/pages/Prodotto";
import Categoria from "@/pages/Categoria";
import MenuSingolo from "@/pages/MenuSingolo";
import Home from "@/components/Home";
import Tavolo from "@/pages/Tavolo";
import StampaParziale from "@/pages/StampaParziale";
import ModificaTavolo from "@/pages/ModificaTavolo";

const routes = [
  {
    path: "/",
    component: Home,
    meta: { loginPage: true, nonRequiresAuth: true },
  },
  {
    path: "/",
    component: DashboardLayout,
    redirect: "/dashboard",
    children: [
      {
        path: "dashboard",
        name: "dashboard",
        component: Dashboard,
      },
      {
        path: "stats",
        name: "stats",
        component: UserProfile
      },
      {
        path: "notifications",
        name: "notifications",
        component: Notifications
      },
      {
        path: "icons",
        name: "icons",
        component: Icons
      },
      {
        path: "maps",
        name: "maps",
        component: Maps
      },
      {
        path: "prodotti",
        name: "prodotti",
        component: Prodotti
      },
      {
        path: "prodotto/:idProdotto",
        name: "prodotto",
        props: true,
        component: Prodotto
      },
      {
        path: "aggiungi-prodotto",
        name: "aggiungi prodotto",
        component: AggiungiProdotto
      },
      {
        path: "modifica-prodotto/:idProdotto",
        name: "modifica prodotto",
        props: true,
        component: ModificaProdotto
      },
      {
        path: "modifica-tavolo/:idTavolo",
        name: "modifica tavolo",
        props: true,
        component: ModificaTavolo
      },
      {
        path: "categorie",
        name: "categorie",
        component: Categorie
      },
      {
        path: "categoria/:idCategoria",
        name: "categoria",
        props: true,
        component: Categoria
      },
      {
        path: "aggiungi-categoria",
        name: "aggiungi categoria",
        component: AggiungiCategoria
      },
      {
        path: "modifica-categoria/:idCategoria",
        name: "modifica categoria",
        props: true,
        component: ModificaCategoria
      },
      {
        path: "menu",
        name: "Lista Menu",
        component: Menu
      },
      {
        path: "menu/:idMenu",
        name: "menu",
        props: true,
        component: MenuSingolo
      },
      {
        path: "aggiungi-menu",
        name: "aggiungi menu",
        component: AggiungiMenu
      },
      {
        path: "modifica-menu/:idMenu",
        name: "modifica menu",
        props: true,
        component: ModificaMenu
      },
      {
        path: "tavoli",
        name: "Tavoli",
        component: Tavoli
      },
      {
        path: "stampa-parziale/:idTavolo",
        name: "Stampa Parziale",
        props: true,
        component: StampaParziale
      },
      {
        path: "tavolo/:idTavolo",
        name: "tavolo",
        props: true,
        component: Tavolo
      },
      {
        path: "tavoli-all",
        name: "Tavoli Vecchi",
        component: TavoliAll
      },
      {
        path: "aggiungi-tavolo",
        name: "aggiungi tavolo",
        component: AggiungiTavolo
      },
      {
        path: "aggiungi-ordine/:idTavolo",
        name: "aggiungi ordine",
        props: true,
        component: AggiungiPrenotazione
      },
    ]
  }
];

/**
 * Asynchronously load view (Webpack Lazy loading compatible)
 * The specified component must be inside the Views folder
 * @param  {string} name  the filename (basename) of the view to load.
function view(name) {
   var res= require('../components/Dashboard/Views/' + name + '.vue');
   return res;
};**/

export default routes;
