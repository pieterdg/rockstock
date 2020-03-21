import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'specimen',
        loadChildren: () => import('./specimen/specimen.module').then(m => m.RockstockSpecimenModule)
      },
      {
        path: 'specimen-status',
        loadChildren: () => import('./specimen-status/specimen-status.module').then(m => m.RockstockSpecimenStatusModule)
      },
      {
        path: 'mineral',
        loadChildren: () => import('./mineral/mineral.module').then(m => m.RockstockMineralModule)
      },
      {
        path: 'series',
        loadChildren: () => import('./series/series.module').then(m => m.RockstockSeriesModule)
      },
      {
        path: 'picture',
        loadChildren: () => import('./picture/picture.module').then(m => m.RockstockPictureModule)
      },
      {
        path: 'nickel-strunz-level-one',
        loadChildren: () =>
          import('./nickel-strunz-level-one/nickel-strunz-level-one.module').then(m => m.RockstockNickelStrunzLevelOneModule)
      },
      {
        path: 'nickel-strunz-level-two',
        loadChildren: () =>
          import('./nickel-strunz-level-two/nickel-strunz-level-two.module').then(m => m.RockstockNickelStrunzLevelTwoModule)
      },
      {
        path: 'nickel-strunz-level-three',
        loadChildren: () =>
          import('./nickel-strunz-level-three/nickel-strunz-level-three.module').then(m => m.RockstockNickelStrunzLevelThreeModule)
      },
      {
        path: 'location',
        loadChildren: () => import('./location/location.module').then(m => m.RockstockLocationModule)
      },
      {
        path: 'country',
        loadChildren: () => import('./country/country.module').then(m => m.RockstockCountryModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class RockstockEntityModule {}
