import { Route } from '@angular/router';

import { RockToolsComponent } from './rock-tools.component';

export const rockToolsRoute: Route = {
  path: '',
  component: RockToolsComponent,
  data: {
    pageTitle: 'rockTools.title'
  }
};
